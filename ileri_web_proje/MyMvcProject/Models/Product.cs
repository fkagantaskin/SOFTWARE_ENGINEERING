using System;
using System.ComponentModel.DataAnnotations;
using System.ComponentModel.DataAnnotations.Schema;
using Microsoft.AspNetCore.Http;

namespace MyMvcProject.Models
{
    public class Product
    {
        public int ProductId { get; set; }

        [Required(ErrorMessage = "Ürün adı gereklidir.")]
        [StringLength(100, ErrorMessage = "Ürün adı en fazla 100 karakter olabilir.")]
        public string Name { get; set; } = string.Empty;

        [Required(ErrorMessage = "Fiyat gereklidir.")]
        [Range(0.01, 1000000, ErrorMessage = "Geçerli bir fiyat giriniz.")]
        public decimal Price { get; set; }

        [Required(ErrorMessage = "Kategori seçmeniz gereklidir.")]
        public int CategoryId { get; set; }

        [DataType(DataType.MultilineText)]
        [StringLength(500, ErrorMessage = "Açıklama en fazla 500 karakter olabilir.")]
        public string? Description { get; set; }

        [NotMapped]
        [Display(Name = "Ürün Görseli")]
        public IFormFile? ImageFile { get; set; }

        public string ImageFileName { get; set; } = string.Empty;

        public virtual Category Category { get; set; } = null!;

        public DateTime CreatedDate { get; set; } = DateTime.Now;
    }
}
