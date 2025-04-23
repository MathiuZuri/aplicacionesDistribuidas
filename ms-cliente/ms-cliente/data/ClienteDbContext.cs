using Microsoft.EntityFrameworkCore;
using ms_cliente.entity; // Asegúrate de tener este namespace

namespace ms_cliente.data
{
    public class ClienteDbContext : DbContext
    {
        // Constructor que recibe las opciones de configuración
        public ClienteDbContext(DbContextOptions<ClienteDbContext> options) : base(options)
        {
        }

        // DbSet para la entidad Cliente. El nombre "Clientes" será el nombre de la tabla (por defecto)
        public DbSet<Cliente> Clientes { get; set; }
        
        protected override void OnModelCreating(ModelBuilder modelBuilder)
        {
            modelBuilder.Entity<Cliente>().ToTable("clientes"); // Asegúrate de que el nombre coincida
            // ... otras configuraciones
        }
    }
}