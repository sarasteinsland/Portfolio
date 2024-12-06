using Microsoft.AspNetCore.Mvc;
using Microsoft.EntityFrameworkCore;
using TrumpVerse.Models;

namespace TrumpVerse.Controllers
{
    [Route("api/[controller]")]
    [ApiController]
    public class TrumpThoughtController : ControllerBase
    {
        private readonly TrumpDbContext _context;

        public TrumpThoughtController(TrumpDbContext context)
        {
            _context = context;
        }

        // GET: api/TrumpThought
        [HttpGet]
        public async Task<ActionResult<IEnumerable<TrumpThought>>> GetTrumpThoughts()
        {
            return await _context.TrumpThoughts.ToListAsync();
        }

        // GET: api/TrumpThought/5
        [HttpGet("{id}")]
        public async Task<ActionResult<TrumpThought>> GetTrumpThought(int id)
        {
            var trumpThought = await _context.TrumpThoughts.FindAsync(id);

            if (trumpThought == null)
            {
                return NotFound();
            }

            return trumpThought;
        }

        // POST: api/TrumpThought
        [HttpPost]
        public async Task<ActionResult<TrumpThought>> PostTrumpThought(TrumpThought trumpThought)
        {
            _context.TrumpThoughts.Add(trumpThought);
            await _context.SaveChangesAsync();

            return CreatedAtAction("GetTrumpThought", new { id = trumpThought.Id }, trumpThought);
        }

        // PUT: api/TrumpThought/5
        [HttpPut("{id}")]
        public async Task<IActionResult> PutTrumpThought(int id, TrumpThought trumpThought)
        {
            if (id != trumpThought.Id)
            {
                return BadRequest();
            }

            _context.Entry(trumpThought).State = EntityState.Modified;

            try
            {
                await _context.SaveChangesAsync();
            }
            catch (DbUpdateConcurrencyException)
            {
                if (!TrumpThoughtExists(id))
                {
                    return NotFound();
                }
                else
                {
                    throw;
                }
            }

            return NoContent();
        }

        // DELETE: api/TrumpThought/5
        [HttpDelete("{id}")]
        public async Task<IActionResult> DeleteTrumpThought(int id)
        {
            var trumpThought = await _context.TrumpThoughts.FindAsync(id);
            if (trumpThought == null)
            {
                return NotFound();
            }

            _context.TrumpThoughts.Remove(trumpThought);
            await _context.SaveChangesAsync();

            return NoContent();
        }

        private bool TrumpThoughtExists(int id)
        {
            return _context.TrumpThoughts.Any(e => e.Id == id);
        }
    }
}
