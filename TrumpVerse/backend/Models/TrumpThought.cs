using System;

namespace TrumpVerse.Models
{
    public class TrumpThought
    {
        public int Id { get; set; }
        public string Title { get; set; }
        public string Content { get; set; }
        public DateTime Date { get; set; }
    }
}