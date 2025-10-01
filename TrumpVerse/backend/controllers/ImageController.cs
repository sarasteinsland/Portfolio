using Microsoft.AspNetCore.Mvc;
using Microsoft.AspNetCore.Hosting;
using System.IO;

namespace TrumpVerse.Controllers
{
    [ApiController]
    [Route("api/[controller]")]
    public class UploadImageController : ControllerBase
    {
        private readonly IWebHostEnvironment hosting;

        public UploadImageController(IWebHostEnvironment _hosting)
        {
            hosting = _hosting;
        }

        [HttpPost]
        public IActionResult SaveImage(IFormFile file)
        {
            if (file == null || file.Length == 0)
            {
                return BadRequest("No file uploaded.");
            }

            var fileName = Path.GetFileName(file.FileName);
            string rootPath = hosting.ContentRootPath; 
            string absolutePath = Path.Combine(rootPath, "images", fileName); 
            var directory = Path.GetDirectoryName(absolutePath);

            if (!Directory.Exists(directory))
            {
                Directory.CreateDirectory(directory);
            }

            using (var fileStream = new FileStream(absolutePath, FileMode.Create))
            {
                file.CopyTo(fileStream);
            }

            return Ok(new { FileName = fileName });
        }

        [HttpDelete("Delete/{fileName}")]
        public IActionResult DeleteImage(string fileName)
        {
            string rootPath = hosting.ContentRootPath; 
            var filePath = Path.Combine(rootPath, "images", fileName); 

            if (!System.IO.File.Exists(filePath))
            {
                return NotFound("File not found.");
            }

            System.IO.File.Delete(filePath);
            return NoContent();
        }
    }
}