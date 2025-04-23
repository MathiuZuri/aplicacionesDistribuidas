using System.Collections.Generic;
using ms_cliente.entity;
using Microsoft.EntityFrameworkCore;
using ms_cliente.data;

namespace ms_cliente.repository
{
    public interface IClienteRepository
    {
        List<Cliente> Listar();
        Cliente Guardar(Cliente cliente);
        Cliente BuscarPorId(int id);
        Cliente Actualizar(Cliente cliente);
        void Eliminar(int id);
    }

    public class ClienteRepository : IClienteRepository
    {
        private readonly ClienteDbContext _context;

        public ClienteRepository(ClienteDbContext context)
        {
            _context = context;
        }

        public List<Cliente> Listar()
        {
            return _context.Clientes.ToList();
        }

        public Cliente Guardar(Cliente cliente)
        {
            _context.Clientes.Add(cliente);
            _context.SaveChanges();
            return cliente;
        }

        public Cliente BuscarPorId(int id)
        {
            return _context.Clientes.Find(id);
        }

        public Cliente Actualizar(Cliente cliente)
        {
            _context.Entry(cliente).State = EntityState.Modified;
            _context.SaveChanges();
            return cliente;
        }

        public void Eliminar(int id)
        {
            var cliente = _context.Clientes.Find(id);
            if (cliente != null)
            {
                _context.Clientes.Remove(cliente);
                _context.SaveChanges();
            }
        }
    }
}