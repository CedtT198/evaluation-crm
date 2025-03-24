using System.Diagnostics;
using Newtonsoft.Json;
using Microsoft.AspNetCore.Mvc;
using newapp_crm.Models;

namespace newapp_crm.Controllers;

public class DepensesController : Controller
{
    private readonly HttpClient _httpClient;

    public DepensesController(IHttpClientFactory httpClientFactory)
    {
        _httpClient = httpClientFactory.CreateClient();
    }

    public async Task<IActionResult> General()
    {
        var httpClient = new HttpClient { BaseAddress = new Uri("http://localhost:8080/") };
        var depensesService = new DepensesService(httpClient);

        var depenses = await depensesService.GetAllDepensesAsync();
        return View(depenses);
    }


    public async Task<IActionResult> Ticket()
    {
        var httpClient = new HttpClient { BaseAddress = new Uri("http://localhost:8080/") };
        var depensesService = new DepensesService(httpClient);

        var depenses = await depensesService.GetAllDepensesAsync();
        return View(depenses);
    }

    
    public async Task<IActionResult> Lead()
    {
        var httpClient = new HttpClient { BaseAddress = new Uri("http://localhost:8080/") };
        var depensesService = new DepensesService(httpClient);

        var depenses = await depensesService.GetAllDepensesAsync();
        return View(depenses);
    }
}