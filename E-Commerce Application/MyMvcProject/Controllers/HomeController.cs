using Microsoft.AspNetCore.Mvc;

namespace MyMvcProject.Controllers
{
    public class HomeController : Controller
    {
        public IActionResult Index()
        {
            if (User.Identity?.IsAuthenticated ?? false)
            {
                ViewBag.Message = $"Hoşgeldiniz, {User.Identity.Name}!";
            }
            else
            {
                ViewBag.Message = "Ana Sayfa - MyMvcProject";
            }

            return View();
        }

        public IActionResult About()
        {
            return View();
        }

        public IActionResult Contact()
        {
            return View();
        }
    }
}
