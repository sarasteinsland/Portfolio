using Microsoft.EntityFrameworkCore;
using TrumpVerse.Models;

namespace TrumpVerse.Models
{
    public class TrumpDbContext : DbContext 
    {
        public TrumpDbContext(DbContextOptions<TrumpDbContext> options) : base(options)
        {
        }
        public required DbSet<TrumpMerchandise> TrumpMerchandise { get; set; }
    }
}
