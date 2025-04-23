using System.Collections.Generic;
using ms_cliente.entity;

namespace ms_cliente.service
{
    public interface IClienteService
    {
        List<Cliente> Listar();
        Cliente Guardar(Cliente cliente);
        Cliente Actualizar(Cliente cliente);
        Cliente BuscarPorId(int id);
        void Eliminar(int id);
    }
}