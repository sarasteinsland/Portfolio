using Microsoft.EntityFrameworkCore;
using TrumpVerse.Models;

var builder = WebApplication.CreateBuilder(args);

builder.Services.AddDbContext<TrumpDbContext>(
    options => options.UseSqlite("Data Source=TrumpVerse.db")
);

builder.Services.AddControllers();
builder.Services.AddEndpointsApiExplorer();
builder.Services.AddSwaggerGen();

builder.Services.AddCors(options =>
{
    options.AddPolicy("AllowAll",
        builder => builder
            .AllowAnyHeader()
            .AllowAnyMethod()
            .AllowAnyOrigin());
});

var app = builder.Build();
DefaultFilesOptions options = new DefaultFilesOptions();
options.DefaultFileNames.Add("index.html");

app.UseDefaultFiles(options);

app.UseStaticFiles();

if (app.Environment.IsDevelopment())
{
    app.UseSwagger();
    app.UseSwaggerUI();
}

app.UseCors("AllowAll"); 

app.UseHttpsRedirection();

app.UseAuthorization();

app.MapControllers();
app.Run();