using Microsoft.EntityFrameworkCore;
using TrumpVerse.Models;

var builder = WebApplication.CreateBuilder(args);

builder.Services.AddDbContext<TrumpDbContext>(
    options => options.UseSqlite("Data Source=TrumpVerse.db")
);

// Add services to the container.

builder.Services.AddControllers();
builder.Services.AddEndpointsApiExplorer();
builder.Services.AddSwaggerGen();

builder.Services.AddCors(
    options => {
        options.AddPolicy("AllowAll",
            builder => builder
                .AllowAnyHeader()
                .AllowAnyMethod()
                .AllowAnyOrigin()
        );
    }
);

var app = builder.Build();

// Setter applikasjonen til å benytte CORS-konfirasjonen over
app.UseCors("AllowAll");
// Åpner opp wwwroot for bruk
DefaultFilesOptions options = new DefaultFilesOptions();
options.DefaultFileNames.Add("index.html");
app.UseDefaultFiles(options);
app.UseStaticFiles();

// Configure the HTTP request pipeline.
if (app.Environment.IsDevelopment())
{
    app.UseSwagger();
    app.UseSwaggerUI();
}

app.UseHttpsRedirection();

app.UseStaticFiles();

app.UseAuthorization();

app.MapControllers();

app.Run();