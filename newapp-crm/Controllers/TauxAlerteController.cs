using System;
using Newtonsoft.Json;
using System.Collections.Generic;
using System.Net.Http;
using System.Text;
using Microsoft.AspNetCore.Mvc;
using System.Threading.Tasks;
using newapp_crm.Models;


namespace newapp_crm.Controllers;

public class TauxAlerteController : Controller
{
    private readonly HttpClient _httpClient;

    // Injection de IHttpClientFactory pour créer HttpClient
    public TauxAlerteController(IHttpClientFactory httpClientFactory)
    {
        _httpClient = httpClientFactory.CreateClient();
    }

    public IActionResult Update()
    {
        return View();
    }

    // Action pour traiter la soumission du formulaire
    [HttpPost]
    public async Task<IActionResult> Update(TauxAlerte tauxAlerte)
    {
        try
        {
            // Sérialiser l'objet TauxAlerte en JSON
            string json = JsonConvert.SerializeObject(tauxAlerte);
            var content = new StringContent(json, Encoding.UTF8, "application/json");

            // Envoyer la requête POST à l'API Spring Boot
            HttpResponseMessage response = await _httpClient.PostAsync("http://localhost:8080/api/taux-alerte/save", content);

            // Vérifier le statut de la réponse
            if (response.IsSuccessStatusCode)
            {
                // Succès : redirection avec un message
                TempData["Message"] = "Insertion done successfuly !";
                // return RedirectToAction("Index");
                return View();
            }
            else
            {
                // Erreur : lire le message renvoyé par l'API
                string errorMessage = await response.Content.ReadAsStringAsync();
                ModelState.AddModelError(string.Empty, errorMessage);
                return View(tauxAlerte); // Retourner le formulaire avec l'erreur
            }
        }
        catch (Exception ex)
        {
            // Erreur de communication (ex. : API inaccessible)
            ModelState.AddModelError(string.Empty, "Error : " + ex.Message);
            return View(tauxAlerte);
        }
    }
}