using System.Collections.Generic;
using ms_cliente.entity;
using ms_cliente.repository;

namespace ms_cliente.service.impl
{
    public class ClienteServiceImpl : IClienteService
    {
        private readonly IClienteRepository _clienteRepository;

        public ClienteServiceImpl(IClienteRepository clienteRepository)
        {
            _clienteRepository = clienteRepository;
        }

        public List<Cliente> Listar()
        {
            return _clienteRepository.Listar();
        }

        public Cliente Guardar(Cliente cliente)
        {
            return _clienteRepository.Guardar(cliente);
        }

        public Cliente Actualizar(Cliente cliente)
        {
            return _clienteRepository.Actualizar(cliente);
        }

        public Cliente BuscarPorId(int id)
        {
            return _clienteRepository.BuscarPorId(id);
        }

        public void Eliminar(int id)
        {
            _clienteRepository.Eliminar(id);
        }
    }
}