using Microsoft.AspNetCore.Mvc;
using Microsoft.EntityFrameworkCore;
using TrumpVerse.Models;

namespace TrumpVerse.Controllers
{
    [ApiController]
    [Route("api/[controller]")]
    public class TrumpMerchandiseController : ControllerBase
    {
        private readonly TrumpDbContext _context;

        public TrumpMerchandiseController(TrumpDbContext context)
        {
            _context = context;
        }

        [HttpGet]
        public async Task<ActionResult<List<TrumpMerchandise>>> Get()
        {
            var merchList = await _context.TrumpMerchandise.ToListAsync();
            return Ok(merchList);
        }

        [HttpGet("{id}")]
        public async Task<ActionResult<TrumpMerchandise>> GetById(int id)
        {
            var merch = await _context.TrumpMerchandise.FindAsync(id);
            if (merch != null)
            {
                return Ok(merch);
            }
            return NotFound();
        }

        [HttpPost]
        public async Task<ActionResult<TrumpMerchandise>> Post([FromBody] TrumpMerchandise newMerch)
        {
            if (newMerch == null || string.IsNullOrEmpty(newMerch.Name) || string.IsNullOrEmpty(newMerch.Description) || newMerch.Price <= 0)
            {
                return BadRequest("Invalid merchandise data.");
            }

            _context.TrumpMerchandise.Add(newMerch);
            await _context.SaveChangesAsync();

            return CreatedAtAction(nameof(GetById), new { id = newMerch.Id }, newMerch);
        }

        [HttpDelete("{id}")]
        public async Task<IActionResult> Delete(int id)
        {
            var merch = await _context.TrumpMerchandise.FindAsync(id);
            if (merch == null)
            {
                return NotFound();
            }

            _context.TrumpMerchandise.Remove(merch);
            await _context.SaveChangesAsync();
            return NoContent();
        }

        [HttpPut("{id}")]
        public async Task<IActionResult> Put(int id, [FromBody] TrumpMerchandise updatedMerch)
        {
            var merch = await _context.TrumpMerchandise.FindAsync(id);
            if (merch == null)
            {
                return NotFound();
            }

            _context.Entry(merch).CurrentValues.SetValues(updatedMerch);
            _context.Entry(merch).State = EntityState.Modified;
            await _context.SaveChangesAsync();

            return NoContent();
        }
    }
}
