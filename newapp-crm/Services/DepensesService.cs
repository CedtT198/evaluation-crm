using System;
using System.Net.Http;
using System.Text;
using System.Text.Json;
using System.Threading.Tasks;
using newapp_crm.Models;

namespace newapp_crm.Services;

public class DepensesService : IDepensesService
{
    private readonly HttpClient _httpClient;

    public DepensesService(HttpClient httpClient)
    {
        _httpClient = httpClient;
    }
    
    public async Task<List<Depenses>> GetAllDepensesAsync()
    {
        try
        {
            // Appel GET à l'API
            HttpResponseMessage response = await _httpClient.GetAsync("api/depenses/all");

            if (response.IsSuccessStatusCode)
            {
                // Lire et désérialiser la réponse JSON
                string json = await response.Content.ReadAsStringAsync();
                return JsonConvert.DeserializeObject<List<Depenses>>(json);
            }
            else
            {
                throw new Exception($"Erreur lors de la récupération des données. Statut : {response.StatusCode}");
            }
        }
        catch (Exception ex)
        {
            throw new Exception("Une erreur est survenue lors de l'appel à l'API.", ex);
        }
    }
}
