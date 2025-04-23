using Microsoft.AspNetCore.HttpOverrides;
using Microsoft.AspNetCore.HttpsPolicy;
using Microsoft.EntityFrameworkCore;
using ms_cliente.data;
using ms_cliente.repository;
using ms_cliente.service;
using ms_cliente.service.impl;
using MySql.EntityFrameworkCore; // Importa el namespace para MySQL
using Microsoft.EntityFrameworkCore;
using ms_cliente.data;
using Steeltoe.Discovery.Client;


var builder = WebApplication.CreateBuilder(args);

builder.Configuration.AddJsonFile("ms-cliente-service.json", optional: true, reloadOnChange: true);
builder.Configuration.AddJsonFile($"ms-cliente-service.{builder.Environment.EnvironmentName}.json", optional: true, reloadOnChange: true);

// Add services to the container.
builder.Services.AddControllers();
// Learn more about configuring Swagger/OpenAPI at https://aka.ms/aspnetcore/swashbuckle
builder.Services.AddEndpointsApiExplorer();
builder.Services.AddSwaggerGen();

builder.Services.AddControllers();
builder.Services.AddDiscoveryClient(builder.Configuration); // Agrega el cliente de descubrimiento
// Learn more about configuring Swagger/OpenAPI at https://aka.ms/aspnetcore/swashbuckle
builder.Services.AddEndpointsApiExplorer();
builder.Services.AddSwaggerGen();



// Configure DbContext with MySQL
var connectionString = builder.Configuration.GetConnectionString("DefaultConnection");
builder.Services.AddDbContext<ClienteDbContext>(options =>
    options.UseMySQL(connectionString));

// Configure Dependency Injection
builder.Services.AddScoped<IClienteRepository, ClienteRepository>();
builder.Services.AddScoped<IClienteService, ClienteServiceImpl>();


var app = builder.Build();

using (var scope = app.Services.CreateScope())
{
    var dbContext = scope.ServiceProvider.GetRequiredService<ClienteDbContext>();
    dbContext.Database.Migrate();
}


app.UseForwardedHeaders(new ForwardedHeadersOptions
{
    ForwardedHeaders = ForwardedHeaders.XForwardedProto
});

app.UseHttpsRedirection();

// Configure the HTTP request pipeline.
if (app.Environment.IsDevelopment())
{
    app.UseSwagger();
    app.UseSwaggerUI(c =>
    {
        c.SwaggerEndpoint("/swagger/v1/swagger.json", "ms-cliente API V1");
        c.RoutePrefix = builder.Configuration["Swagger:RoutePrefix"]; // Lee la ruta del prefijo de la configuración http://localhost:5201/swagger/index.html
    });
}



app.UseHttpsRedirection();

app.UseAuthorization();

app.MapControllers();

app.UseDiscoveryClient(); // Habilita el middleware para el registro con Eureka

app.Run();
