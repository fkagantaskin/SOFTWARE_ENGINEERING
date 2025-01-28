using System.ComponentModel.DataAnnotations;

namespace MyMvcProject.ViewModels
{
    public class ContactViewModel
    {
        [Required(ErrorMessage = "Adınız gereklidir.")]
        public string Name { get; set; } = string.Empty;

        [Required(ErrorMessage = "E-posta adresiniz gereklidir.")]
        [EmailAddress(ErrorMessage = "Geçerli bir e-posta adresi giriniz.")]
        public string Email { get; set; } = string.Empty;

        [Required(ErrorMessage = "Mesajınız gereklidir.")]
        public string Message { get; set; } = string.Empty;
    }
}
