namespace ms_cliente.entity
{
    public class Cliente
    {
        public int ClienteId { get; set; }
        public string NombreCompleto { get; set; }
        public string DNI { get; set; }
        public string Direccion { get; set; }
        public DateTime FechaNacimiento { get; set; }

        // Constructor
        public Cliente( int clienteId, string nombreCompleto, string dni, string direccion, DateTime fechaNacimiento)
        {
            ClienteId = clienteId;
            NombreCompleto = nombreCompleto;
            DNI = dni;
            Direccion = direccion;
            FechaNacimiento = fechaNacimiento;
        }
        
        public Cliente () {}

        // Método ToString()
        public override string ToString()
        {
            return $"id: {ClienteId}, Nombre: {NombreCompleto}, DNI: {DNI}, Dirección: {Direccion}, Fecha de Nacimiento: {FechaNacimiento}";
        }
    }
}