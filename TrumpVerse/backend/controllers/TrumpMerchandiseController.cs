using Microsoft.AspNetCore.Mvc;
using Microsoft.EntityFrameworkCore;
using TrumpVerse.Models;

namespace TrumpVerse.Controllers
{
    [Route("api/[controller]")]
    [ApiController]
    public class TrumpMerchandiseController : ControllerBase
    {
        private readonly TrumpDbContext _context;

        public TrumpMerchandiseController(TrumpDbContext context)
        {
            _context = context;
        }

        // GET: api/TrumpMerchandise
        [HttpGet]
        public async Task<ActionResult<IEnumerable<TrumpMerchandise>>> GetTrumpMerchandise()
        {
            return await _context.TrumpMerchandise.ToListAsync();
        }

        // POST: api/TrumpMerchandise
        [HttpPost]
        public async Task<ActionResult<TrumpMerchandise>> PostTrumpMerchandise([FromForm] TrumpMerchandise merch, IFormFile image)
        {
            var imagePath = Path.Combine("wwwroot/images/Merch", image.FileName);
            using (var stream = new FileStream(imagePath, FileMode.Create))
            {
                await image.CopyToAsync(stream);
            }
            merch.Image = image.FileName;

            _context.TrumpMerchandise.Add(merch);
            await _context.SaveChangesAsync();

            return CreatedAtAction("GetTrumpMerchandise", new { id = merch.Id }, merch);
        }

        // DELETE: api/TrumpMerchandise/5
        [HttpDelete("{id}")]
        public async Task<IActionResult> DeleteTrumpMerchandise(int id)
        {
            var trumpMerchandise = await _context.TrumpMerchandise.FindAsync(id);
            if (trumpMerchandise == null)
            {
                return NotFound();
            }

            var imagePath = Path.Combine("wwwroot/images/Merch", trumpMerchandise.Image);
            if (System.IO.File.Exists(imagePath))
            {
                System.IO.File.Delete(imagePath);
            }

            _context.TrumpMerchandise.Remove(trumpMerchandise);
            await _context.SaveChangesAsync();

            return NoContent();
        }
    }
}
