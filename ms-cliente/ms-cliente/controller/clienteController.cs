using System.Collections.Generic;
using Microsoft.AspNetCore.Mvc;
using ms_cliente.entity;
using ms_cliente.service;
using System.Net;

namespace ms_cliente.controller
{
    [ApiController]
    [Route("clientes")]
    public class ClienteController : ControllerBase
    {
        private readonly IClienteService _clienteService;

        public ClienteController(IClienteService clienteService)
        {
            _clienteService = clienteService;
        }

        [HttpGet]
        public ActionResult<IEnumerable<Cliente>> Listar()
        {
            return Ok(_clienteService.Listar());
        }

        [HttpPost]
        [ProducesResponseType(StatusCodes.Status201Created)]
        public ActionResult<Cliente> Guardar([FromBody] Cliente cliente)
        {
            var nuevoCliente = _clienteService.Guardar(cliente);
            return CreatedAtAction(nameof(BuscarPorId), new { id = nuevoCliente.DNI }, nuevoCliente); // Assuming DNI is a unique identifier
        }

        [HttpPut("{id}")]
        [ProducesResponseType(StatusCodes.Status200OK)]
        [ProducesResponseType(StatusCodes.Status404NotFound)]
        public ActionResult<Cliente> Actualizar(int id, [FromBody] Cliente clienteActualizado)
        {
            var clienteExistente = _clienteService.BuscarPorId(id);
            if (clienteExistente == null)
            {
                return NotFound();
            }

            // Actualiza las propiedades de la entidad existente con los valores del DTO/entidad actualizada
            clienteExistente.NombreCompleto = clienteActualizado.NombreCompleto;
            clienteExistente.DNI = clienteActualizado.DNI;
            clienteExistente.Direccion = clienteActualizado.Direccion;
            clienteExistente.FechaNacimiento = clienteActualizado.FechaNacimiento;
            // No intentes modificar la clave primaria (ClienteId) a menos que sea un caso de uso específico

            var clienteResult = _clienteService.Actualizar(clienteExistente); // Ahora pasamos la entidad rastreada y modificada
            return Ok(clienteResult);
        }

        [HttpGet("{id}")]
        [ProducesResponseType(StatusCodes.Status200OK)]
        [ProducesResponseType(StatusCodes.Status404NotFound)]
        public ActionResult<Cliente> BuscarPorId(int id)
        {
            var cliente = _clienteService.BuscarPorId(id);
            if (cliente == null)
            {
                return NotFound();
            }
            return Ok(cliente);
        }

        [HttpDelete("{id}")]
        [ProducesResponseType(StatusCodes.Status204NoContent)]
        [ProducesResponseType(StatusCodes.Status404NotFound)]
        public IActionResult EliminarPorId(int id)
        {
            var clienteExistente = _clienteService.BuscarPorId(id);
            if (clienteExistente == null)
            {
                return NotFound();
            }
            _clienteService.Eliminar(id);
            return NoContent();
        }
    }
}