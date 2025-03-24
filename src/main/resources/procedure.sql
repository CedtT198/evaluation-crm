-- 
-- employee
-- 
-- DELIMITER //

-- CREATE PROCEDURE generate_random_employees(IN num INT)
-- BEGIN
--     DECLARE i INT DEFAULT 0;
    
--     WHILE i < num DO
--         INSERT INTO employee(username, first_name, last_name, email, password, provider)
--         VALUES (
--             CONCAT('user', FLOOR(RAND() * 10000)),
--             CONCAT('First', FLOOR(RAND() * 10000)),
--             CONCAT('Last', FLOOR(RAND() * 10000)),
--             CONCAT('email', FLOOR(RAND() * 10000), '@example.com'),
--             SHA2(CONCAT('password', FLOOR(RAND() * 10000)), 256),
--             IF(RAND() > 0.5, 'provider1', 'provider2')
--         );
--         SET i = i + 1;
--     END WHILE;
-- END//

-- DELIMITER ;

-- CALL generate_random_employees(10);
-- 
-- 
-- 




-- 
-- email template
-- 
-- DELIMITER //

-- CREATE PROCEDURE generate_random_email_templates(IN num INT)
-- BEGIN
--     DECLARE i INT DEFAULT 0;
    
--     WHILE i < num DO
--         INSERT INTO email_template(name, content, json_design, created_at)
--         VALUES (
--             CONCAT('Template', FLOOR(RAND() * 10000)),
--             CONCAT('Subject ', FLOOR(RAND() * 10000)),
--             CONCAT('Hello,\nThis is an auto-generated email template ', FLOOR(RAND() * 10000), '.\nBest regards.'),
--             NOW()
--         );
--         SET i = i + 1;
--     END WHILE;
-- END//

-- DELIMITER ;

-- CALL generate_random_email_templates(5);
-- 
-- 
-- 




-- 
-- customer login info
-- 
DELIMITER //

CREATE PROCEDURE generate_random_customer_logins(IN num INT)
BEGIN
    DECLARE i INT DEFAULT 0;
    
    WHILE i < num DO
        INSERT INTO customer_login_info (username, password, token, password_set)
        VALUES (
            CONCAT('user', FLOOR(RAND() * 10000)), 
            SHA2(CONCAT('pass', FLOOR(RAND() * 10000)), 256), 
            -- '1234', 
            UUID(), 
            FLOOR(RAND() * 2) -- Génère 0 ou 1 pour password_set
        );
        SET i = i + 1;
    END WHILE;
END//

DELIMITER ;

-- CALL generate_random_customer_logins(10);
-- 
-- 
-- 






-- 
-- customer
-- 
DELIMITER //

CREATE PROCEDURE generate_random_customers(IN num INT)
BEGIN
    DECLARE i INT DEFAULT 0;
    DECLARE random_user_id INT;
    DECLARE random_profile_id INT;

    WHILE i < num DO
        -- Sélection d'un user_id existant
        SELECT id INTO random_user_id FROM users ORDER BY RAND() LIMIT 1;

        -- Sélection d'un profile_id existant
        SELECT id INTO random_profile_id FROM customer_login_info ORDER BY RAND() LIMIT 1;

        INSERT INTO customer (
            name, phone, address, city, state, country, 
            user_id, description, position, twitter, facebook, youtube, 
            created_at, email, profile_id
        ) VALUES (
            CONCAT('Customer', FLOOR(RAND() * 10000)),
            CONCAT('+261 34 ', FLOOR(RAND() * 1000000)), 
            CONCAT(FLOOR(RAND() * 1000), ' Rue Random'), 
            'Antananarivo', 
            'Analamanga', 
            'Madagascar', 
            random_user_id, 
            'Client important',
            'Manager',
            CONCAT('https://twitter.com/user', FLOOR(RAND() * 10000)), 
            CONCAT('https://facebook.com/user', FLOOR(RAND() * 10000)), 
            CONCAT('https://youtube.com/user', FLOOR(RAND() * 10000)), 
            NOW(), 
            CONCAT('customer', FLOOR(RAND() * 10000), '@example.com'), 
            random_profile_id
        );

        SET i = i + 1;
    END WHILE;
END//

DELIMITER ;

-- CALL generate_random_customers(10);
-- 
-- 
-- 





-- 
-- trigger lead
-- 
DELIMITER //

CREATE PROCEDURE generate_random_trigger_leads(IN num INT)
BEGIN
    DECLARE i INT DEFAULT 0;
    DECLARE random_customer_id INT;
    DECLARE random_user_id INT;
    DECLARE random_employee_id INT;

    WHILE i < num DO
        -- Sélection d'un customer_id existant
        SELECT customer_id INTO random_customer_id FROM customer ORDER BY RAND() LIMIT 1;

        -- Sélection d'un user_id existant pour l'utilisateur principal
        SELECT id INTO random_user_id FROM users ORDER BY RAND() LIMIT 1;

        -- Sélection d'un employee_id existant pour l'employé assigné au lead
        SELECT id INTO random_employee_id FROM users ORDER BY RAND() LIMIT 1;

        INSERT INTO trigger_lead (
            customer_id, user_id, name, phone, employee_id, status, 
            google_drive, created_at
        ) VALUES (
            random_customer_id, 
            random_user_id, 
            CONCAT('Lead ', FLOOR(RAND() * 10000)),
            CONCAT('+261 34 ', FLOOR(RAND() * 1000000)),
            random_employee_id, 
            'Pending', 
            0, 
            NOW()
        );

        SET i = i + 1;
    END WHILE;
END//

DELIMITER ;

-- CALL generate_random_trigger_leads(10);
-- 
-- 
-- 







-- 
-- trigger ticket
-- 
DELIMITER //

CREATE PROCEDURE generate_random_trigger_tickets(IN num INT)
BEGIN
    DECLARE i INT DEFAULT 0;
    DECLARE random_customer_id INT;
    DECLARE random_manager_id INT;
    DECLARE random_employee_id INT;

    WHILE i < num DO
        -- Sélection d'un customer_id existant
        SELECT customer_id INTO random_customer_id FROM customer ORDER BY RAND() LIMIT 1;

        -- Sélection d'un manager_id existant
        SELECT id INTO random_manager_id FROM users ORDER BY RAND() LIMIT 1;

        -- Sélection d'un employee_id existant
        SELECT id INTO random_employee_id FROM users ORDER BY RAND() LIMIT 1;

        INSERT INTO trigger_ticket (
            subject, description, status, priority, customer_id, manager_id, employee_id, created_at
        ) VALUES (
            CONCAT('Ticket Subject ', FLOOR(RAND() * 10000)),
            CONCAT('Description of the ticket with ID ', FLOOR(RAND() * 10000)),
            CASE
                WHEN FLOOR(RAND() * 3) = 0 THEN 'Open'
                WHEN FLOOR(RAND() * 3) = 1 THEN 'Closed'
                ELSE 'In Progress'
            END,
            CASE
                WHEN FLOOR(RAND() * 2) = 0 THEN 'Low'
                ELSE 'High'
            END,
            random_customer_id, 
            random_manager_id, 
            random_employee_id, 
            NOW()
        );

        SET i = i + 1;
    END WHILE;
END//

DELIMITER ;

-- CALL generate_random_trigger_tickets(10);
-- 
-- 
-- 





-- 
-- trigger_contract
-- 
DELIMITER //

CREATE PROCEDURE generate_random_trigger_contracts(IN num INT)
BEGIN
    DECLARE i INT DEFAULT 0;
    DECLARE random_lead_id INT;
    DECLARE random_user_id INT;
    DECLARE random_customer_id INT;

    WHILE i < num DO
        -- Sélection d'un lead_id existant
        SELECT lead_id INTO random_lead_id FROM trigger_lead ORDER BY RAND() LIMIT 1;

        -- Sélection d'un user_id existant
        SELECT id INTO random_user_id FROM users ORDER BY RAND() LIMIT 1;

        -- Sélection d'un customer_id existant
        SELECT customer_id INTO random_customer_id FROM customer ORDER BY RAND() LIMIT 1;

        INSERT INTO trigger_contract (
            subject, status, description, start_date, end_date, amount, google_drive, google_drive_folder_id, lead_id, user_id, customer_id, created_at
        ) VALUES (
            CONCAT('Contract Subject ', FLOOR(RAND() * 10000)),
            CASE
                WHEN FLOOR(RAND() * 3) = 0 THEN 'Active'
                WHEN FLOOR(RAND() * 3) = 1 THEN 'Expired'
                ELSE 'Pending'
            END,
            CONCAT('Description of the contract with ID ', FLOOR(RAND() * 10000)),
            DATE_ADD(CURDATE(), INTERVAL -FLOOR(RAND() * 365) DAY),
            DATE_ADD(CURDATE(), INTERVAL FLOOR(RAND() * 365) DAY),
            ROUND(RAND() * 10000, 0),
            FLOOR(RAND() * 2),
            CONCAT('google_drive_folder_', FLOOR(RAND() * 10000)),
            random_lead_id, 
            random_user_id, 
            random_customer_id, 
            NOW()
        );

        SET i = i + 1;
    END WHILE;
END//

DELIMITER ;

-- CALL generate_random_trigger_contracts(10);
-- 
-- 
-- 





-- 
-- contract settings
-- 
-- DELIMITER //

-- CREATE PROCEDURE generate_random_contract_settings(IN num INT)
-- BEGIN
--     DECLARE i INT DEFAULT 0;
--     DECLARE random_user_id INT;
--     DECLARE random_customer_id INT;
--     DECLARE random_status_email_template INT;
--     DECLARE random_amount_email_template INT;
--     DECLARE random_subject_email_template INT;
--     DECLARE random_description_email_template INT;
--     DECLARE random_start_email_template INT;
--     DECLARE random_end_email_template INT;

--     WHILE i < num DO
--         -- Sélection aléatoire d'un user_id existant
--         SELECT id INTO random_user_id FROM users ORDER BY RAND() LIMIT 1;

--         -- Sélection aléatoire d'un customer_id existant
--         SELECT id INTO random_customer_id FROM customer_login_info ORDER BY RAND() LIMIT 1;

--         -- Sélection de templates email aléatoires
--         SELECT template_id INTO random_status_email_template FROM email_template ORDER BY RAND() LIMIT 1;
--         SELECT template_id INTO random_amount_email_template FROM email_template ORDER BY RAND() LIMIT 1;
--         SELECT template_id INTO random_subject_email_template FROM email_template ORDER BY RAND() LIMIT 1;
--         SELECT template_id INTO random_description_email_template FROM email_template ORDER BY RAND() LIMIT 1;
--         SELECT template_id INTO random_start_email_template FROM email_template ORDER BY RAND() LIMIT 1;
--         SELECT template_id INTO random_end_email_template FROM email_template ORDER BY RAND() LIMIT 1;

--         -- Insertion des données aléatoires dans la table `contract_settings`
--         INSERT INTO contract_settings (
--             amount, subject, description, end_date, start_date, status, user_id,
--             status_email_template, amount_email_template, subject_email_template,
--             description_email_template, start_email_template, end_email_template, customer_id
--         ) VALUES (
--             FLOOR(RAND() * 2),  -- 0 ou 1 pour amount
--             FLOOR(RAND() * 2),  -- 0 ou 1 pour subject
--             FLOOR(RAND() * 2),  -- 0 ou 1 pour description
--             FLOOR(RAND() * 2),  -- 0 ou 1 pour end_date
--             FLOOR(RAND() * 2),  -- 0 ou 1 pour start_date
--             FLOOR(RAND() * 2),  -- 0 ou 1 pour status
--             random_user_id,
--             random_status_email_template,
--             random_amount_email_template,
--             random_subject_email_template,
--             random_description_email_template,
--             random_start_email_template,
--             random_end_email_template,
--             random_customer_id
--         );

--         SET i = i + 1;
--     END WHILE;
-- END//

-- DELIMITER ;

CALL generate_random_contract_settings(10);
-- 
-- 
-- 




-- 
-- lead_action
-- 
-- DELIMITER //

-- CREATE PROCEDURE generate_random_lead_actions(IN num INT)
-- BEGIN
--     DECLARE i INT DEFAULT 0;
--     DECLARE random_lead_id INT;
--     DECLARE random_action VARCHAR(255);
--     DECLARE random_date_time DATETIME;

--     WHILE i < num DO
--         -- Sélection aléatoire d'un lead_id existant dans trigger_lead
--         SELECT lead_id INTO random_lead_id FROM trigger_lead ORDER BY RAND() LIMIT 1;

--         -- Génération d'une action aléatoire
--         SET random_action = CONCAT('Action ', FLOOR(RAND() * 100));

--         -- Génération d'une date et heure aléatoire
--         SET random_date_time = NOW() - INTERVAL FLOOR(RAND() * 365) DAY + INTERVAL FLOOR(RAND() * 24) HOUR + INTERVAL FLOOR(RAND() * 60) MINUTE;

--         -- Insertion d'une action aléatoire dans la table lead_action
--         INSERT INTO lead_action (lead_id, action, date_time) 
--         VALUES (random_lead_id, random_action, random_date_time);

--         SET i = i + 1;
--     END WHILE;
-- END//

-- DELIMITER ;

-- CALL generate_random_lead_actions(10);
-- 
-- 
-- 






-- 
-- lead_settings
-- 
-- DELIMITER //

-- CREATE PROCEDURE generate_random_lead_settings(IN num INT)
-- BEGIN
--     DECLARE i INT DEFAULT 0;
--     DECLARE random_user_id INT;
--     DECLARE random_customer_id INT;
--     DECLARE random_status TINYINT(1);
--     DECLARE random_meeting TINYINT(1);
--     DECLARE random_phone TINYINT(1);
--     DECLARE random_name TINYINT(1);
--     DECLARE random_status_email_template INT UNSIGNED;
--     DECLARE random_phone_email_template INT UNSIGNED;
--     DECLARE random_meeting_email_template INT UNSIGNED;
--     DECLARE random_name_email_template INT UNSIGNED;

--     WHILE i < num DO
--         -- Sélection aléatoire d'un user_id et d'un customer_id
--         SELECT id INTO random_user_id FROM users ORDER BY RAND() LIMIT 1;
--         SELECT id INTO random_customer_id FROM customer_login_info ORDER BY RAND() LIMIT 1;

--         -- Génération aléatoire pour les autres colonnes
--         SET random_status = FLOOR(RAND() * 2);
--         SET random_meeting = FLOOR(RAND() * 2);
--         SET random_phone = FLOOR(RAND() * 2);
--         SET random_name = FLOOR(RAND() * 2);
--         SET random_status_email_template = (SELECT template_id FROM email_template ORDER BY RAND() LIMIT 1);
--         SET random_phone_email_template = (SELECT template_id FROM email_template ORDER BY RAND() LIMIT 1);
--         SET random_meeting_email_template = (SELECT template_id FROM email_template ORDER BY RAND() LIMIT 1);
--         SET random_name_email_template = (SELECT template_id FROM email_template ORDER BY RAND() LIMIT 1);

--         -- Insertion d'un enregistrement aléatoire dans la table lead_settings
--         INSERT INTO lead_settings (status, meeting, phone, name, user_id, status_email_template, phone_email_template, meeting_email_template, name_email_template, customer_id) 
--         VALUES (random_status, random_meeting, random_phone, random_name, random_user_id, random_status_email_template, random_phone_email_template, random_meeting_email_template, random_name_email_template, random_customer_id);

--         SET i = i + 1;
--     END WHILE;
-- END//

-- DELIMITER ;

-- CALL generate_random_lead_settings(10);
-- 
-- 
-- 




-- 
-- ticket_settings
-- 
-- DELIMITER //

-- CREATE PROCEDURE generate_random_ticket_settings(IN num INT)
-- BEGIN
--     DECLARE i INT DEFAULT 0;
--     DECLARE random_user_id INT;
--     DECLARE random_customer_id INT;
--     DECLARE random_priority TINYINT(1);
--     DECLARE random_status TINYINT(1);
--     DECLARE random_subject TINYINT(1);
--     DECLARE random_description TINYINT(1);
--     DECLARE random_status_email_template INT UNSIGNED;
--     DECLARE random_subject_email_template INT UNSIGNED;
--     DECLARE random_priority_email_template INT UNSIGNED;
--     DECLARE random_description_email_template INT UNSIGNED;

--     WHILE i < num DO
--         -- Sélection aléatoire d'un user_id et d'un customer_id
--         SELECT id INTO random_user_id FROM users ORDER BY RAND() LIMIT 1;
--         SELECT id INTO random_customer_id FROM customer_login_info ORDER BY RAND() LIMIT 1;

--         -- Génération aléatoire pour les autres colonnes
--         SET random_priority = FLOOR(RAND() * 2);
--         SET random_status = FLOOR(RAND() * 2);
--         SET random_subject = FLOOR(RAND() * 2);
--         SET random_description = FLOOR(RAND() * 2);
--         SET random_status_email_template = (SELECT template_id FROM email_template ORDER BY RAND() LIMIT 1);
--         SET random_subject_email_template = (SELECT template_id FROM email_template ORDER BY RAND() LIMIT 1);
--         SET random_priority_email_template = (SELECT template_id FROM email_template ORDER BY RAND() LIMIT 1);
--         SET random_description_email_template = (SELECT template_id FROM email_template ORDER BY RAND() LIMIT 1);

--         -- Insertion d'un enregistrement aléatoire dans la table ticket_settings
--         INSERT INTO ticket_settings (priority, status, subject, description, user_id, status_email_template, subject_email_template, priority_email_template, description_email_template, customer_id) 
--         VALUES (random_priority, random_status, random_subject, random_description, random_user_id, random_status_email_template, random_subject_email_template, random_priority_email_template, random_description_email_template, random_customer_id);

--         SET i = i + 1;
--     END WHILE;
-- END//

-- DELIMITER ;

-- CALL generate_random_ticket_settings(10);
-- 
-- 
-- 







-- 
-- file
-- 
DELIMITER //

CREATE PROCEDURE generate_random_file_data(IN num INT)
BEGIN
    DECLARE i INT DEFAULT 0;
    DECLARE random_lead_id INT;
    DECLARE random_contract_id INT;
    DECLARE random_file_name VARCHAR(100);
    DECLARE random_file_type VARCHAR(255);
    DECLARE random_file_data BLOB;

    WHILE i < num DO
        -- Sélection aléatoire d'un lead_id et d'un contract_id
        SELECT lead_id INTO random_lead_id FROM trigger_lead ORDER BY RAND() LIMIT 1;
        SELECT contract_id INTO random_contract_id FROM trigger_contract ORDER BY RAND() LIMIT 1;

        -- Génération d'un nom de fichier aléatoire et d'un type de fichier
        SET random_file_name = CONCAT('file_', FLOOR(RAND() * 10000), '.pdf');
        SET random_file_type = 'application/pdf';

        -- Génération de données binaires simulées (fichier fictif)
        SET random_file_data = CONCAT('Random file data: ', FLOOR(RAND() * 10000));

        -- Insertion d'un enregistrement aléatoire dans la table file
        INSERT INTO file (file_name, file_data, file_type, lead_id, contract_id) 
        VALUES (random_file_name, random_file_data, random_file_type, random_lead_id, random_contract_id);

        SET i = i + 1;
    END WHILE;
END//

DELIMITER ;

CALL generate_random_file_data(10);
-- 
-- 
-- 






-- 
-- google_drive_file
-- 
DELIMITER //

CREATE PROCEDURE generate_random_google_drive_file_data(IN num INT)
BEGIN
    DECLARE i INT DEFAULT 0;
    DECLARE random_lead_id INT;
    DECLARE random_contract_id INT;
    DECLARE random_drive_file_id VARCHAR(255);
    DECLARE random_drive_folder_id VARCHAR(255);

    WHILE i < num DO
        -- Sélection aléatoire d'un lead_id et d'un contract_id
        SELECT lead_id INTO random_lead_id FROM trigger_lead ORDER BY RAND() LIMIT 1;
        SELECT contract_id INTO random_contract_id FROM trigger_contract ORDER BY RAND() LIMIT 1;

        -- Génération d'un identifiant de fichier et de dossier aléatoires
        SET random_drive_file_id = CONCAT('file_', FLOOR(RAND() * 10000));
        SET random_drive_folder_id = CONCAT('folder_', FLOOR(RAND() * 10000));

        -- Insertion d'un enregistrement aléatoire dans la table google_drive_file
        INSERT INTO google_drive_file (drive_file_id, drive_folder_id, lead_id, contract_id) 
        VALUES (random_drive_file_id, random_drive_folder_id, random_lead_id, random_contract_id);

        SET i = i + 1;
    END WHILE;
END//

DELIMITER ;

CALL generate_random_google_drive_file_data(10);
-- 
-- 
-- 
-- 




-- CALL generate_random_employees(10);
-- CALL generate_random_email_templates(5);
CALL generate_random_customer_logins(10);
CALL generate_random_customers(10);
CALL generate_random_trigger_leads(10);
CALL generate_random_trigger_tickets(10);
CALL generate_random_trigger_contracts(10);
-- CALL generate_random_contract_settings(10);
-- CALL generate_random_lead_actions(10);
-- CALL generate_random_lead_settings(10);
-- CALL generate_random_ticket_settings(10);
-- CALL generate_random_file_data(10);
-- CALL generate_random_google_drive_file_data(10);

