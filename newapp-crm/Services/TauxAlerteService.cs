using System;
using System.Net.Http;
using System.Text;
using System.Text.Json;
using System.Threading.Tasks;
using newapp_crm.Models;

namespace newapp_crm.Services;

public class TauxAlerteService
{
    private readonly HttpClient _httpClient;
    private const string ApiUrl = "http://localhost:8080//api/taux-alerte/save";

    public TauxAlerteService(HttpClient httpClient)
    {
        _httpClient = httpClient;
    }

    public async Task<string> SaveTauxAlerteAsync(TauxAlerte tauxAlerte)
    {
        try
        {
            string jsonData = JsonSerializer.Serialize(tauxAlerte);
            var content = new StringContent(jsonData, Encoding.UTF8, "application/json");

            HttpResponseMessage response = await _httpClient.PostAsync(ApiUrl, content);

            if (response.IsSuccessStatusCode)
            {
                return "Insert done successfuly.";
            }
            else
            {
                return $"Error : {response.StatusCode} - {await response.Content.ReadAsStringAsync()}";
            }
        }
        catch (Exception ex)
        {
            return $"Exception : {ex.Message}";
        }
    }
}
