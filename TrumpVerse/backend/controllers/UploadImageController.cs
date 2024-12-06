using Microsoft.AspNetCore.Mvc;

public class UploadImageController : ControllerBase{
    private readonly IWebHostEnvironment hosting;
    public UploadImageController(IWebHostEnvironment _hosting)
    {
       hosting = _hosting; 

    }
    [HttpPost]
    public IActionResult SaveImage (IFormFile file)
    {
        if (file == null || file.Length == 0)
        {
            return BadRequest("No pictures uploaded.");
        }
        string wwwrootPath = hosting.WebRootPath;
        string absolutePath = Path.Combine(wwwrootPath, "images", file.FileName);

        var directory = Path.GetDirectoryName(absolutePath);
        if (!Directory.Exists(directory))
        {
            Directory.CreateDirectory(directory);
        }

        using(var fileStream = new FileStream(absolutePath, FileMode.Create))

        {
            file.CopyTo(fileStream);

        }
        string imageUrl = $"/images/{file.FileName}";
        return Ok(new{ FileName = file.FileName});
    }
}