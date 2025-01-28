using Microsoft.AspNetCore.Mvc;
using MyMvcProject.Data; 
using MyMvcProject.Models;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks; 
using Microsoft.EntityFrameworkCore; 

namespace MyMvcProject.ViewComponents
{
    public class LatestProductsViewComponent : ViewComponent
    {
        private readonly ApplicationDbContext _context;

        public LatestProductsViewComponent(ApplicationDbContext context)
        {
            _context = context;
        }

        public async Task<IViewComponentResult> InvokeAsync(int count)
        {
            var products = await _context.Products
                                       .OrderByDescending(p => p.CreatedDate)
                                       .Take(count)
                                       .ToListAsync(); 

            return View(products);
        }
    }
}
