CREATE OR REPLACE VIEW depenses AS
SELECT 
    dl.id,
    dl.amount,
    dl.date_depense,
    'Lead' AS source,
    dl.lead_id AS reference_id,
    l.customer_id,
    dl.description,
    dl.summary,
    dl.confirm
FROM depenses_lead dl
JOIN trigger_lead l ON dl.lead_id = l.lead_id

UNION ALL

SELECT 
    dt.id,
    dt.amount,
    dt.date_depense,
    'Ticket' AS source,
    dt.ticket_id AS reference_id,
    t.customer_id,
    dt.description,
    dt.summary,
    dt.confirm
FROM depenses_ticket dt
JOIN trigger_ticket t ON dt.ticket_id = t.ticket_id;


SELECT * FROM depenses d ORDER BY d.customer_id, d.date_depense DESC;
