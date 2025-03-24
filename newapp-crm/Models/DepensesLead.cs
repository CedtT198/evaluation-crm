using System;
using System.ComponentModel.DataAnnotations;

namespace newapp_crm.Models;

public class DepensesLead
{
    public int Id { get; set; }
    public decimal Amount { get; set; }
    public DateTime DateDepense { get; set; }
    public int LeadId { get; set; } 
    public string Description { get; set; }
    public string Summary { get; set; }
    public bool Confirm { get; set; }
}