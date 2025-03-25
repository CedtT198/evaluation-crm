package site.easy.to.build.crm.service.database;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.jdbc.core.JdbcTemplate;

@Service
public class DatabaseServiceImpl implements DatabaseService {
    private final JdbcTemplate jdbcTemplate;

    public DatabaseServiceImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    @Transactional
    public void truncate() throws Exception {
        // String[] tables = {
        //     "google_drive_file", "file", "ticket_settings", "lead_settings",
        //     "lead_action", "contract_settings", "trigger_contract", "trigger_ticket",
        //     "trigger_lead", "customer", "customer_login_info", "email_template", "employee"
        // };

        // StringBuilder sqlBuilder = new StringBuilder();
        // sqlBuilder.append("SET FOREIGN_KEY_CHECKS=0; ");
        // for (String table : tables) {
        //     sqlBuilder.append("TRUNCATE TABLE ").append(table).append("; ");
        // }
        // sqlBuilder.append("SET FOREIGN_KEY_CHECKS=1;");
        // System.err.println(sqlBuilder);
        // jdbcTemplate.execute(sqlBuilder.toString());
        
        jdbcTemplate.execute("SET FOREIGN_KEY_CHECKS=0");

        jdbcTemplate.execute("TRUNCATE TABLE taux_alerte;");
        jdbcTemplate.execute("TRUNCATE TABLE depenses_ticket;");
        jdbcTemplate.execute("TRUNCATE TABLE depenses_lead;");
        jdbcTemplate.execute("TRUNCATE TABLE budget;");
        jdbcTemplate.execute("TRUNCATE TABLE google_drive_file;");
        jdbcTemplate.execute("TRUNCATE TABLE file;");
        jdbcTemplate.execute("TRUNCATE TABLE ticket_settings;");
        jdbcTemplate.execute("TRUNCATE TABLE lead_settings;");
        jdbcTemplate.execute("TRUNCATE TABLE lead_action;");
        jdbcTemplate.execute("TRUNCATE TABLE contract_settings;");
        jdbcTemplate.execute("TRUNCATE TABLE trigger_contract;");
        jdbcTemplate.execute("TRUNCATE TABLE trigger_ticket;");
        jdbcTemplate.execute("TRUNCATE TABLE trigger_lead;");
        // jdbcTemplate.execute("TRUNCATE TABLE customer;");
        // jdbcTemplate.execute("TRUNCATE TABLE customer_login_info;");
        jdbcTemplate.execute("TRUNCATE TABLE email_template;");
        jdbcTemplate.execute("TRUNCATE TABLE employee;");
        
        jdbcTemplate.execute("SET FOREIGN_KEY_CHECKS=1");
    }


    @Override
    public void generateData(int nbLine) throws Exception  {
        if (nbLine <= 0) {
            throw new Exception("Line per table cannot be under 1.");
        }
        
        // jdbcTemplate.execute("CALL generate_random_customer_logins("+nbLine+");");
        // jdbcTemplate.execute("CALL generate_random_customers("+nbLine+");");
        jdbcTemplate.execute("CALL generate_random_trigger_leads("+nbLine+");");
        jdbcTemplate.execute("CALL generate_random_trigger_tickets("+nbLine+");");
        jdbcTemplate.execute("CALL generate_random_trigger_contracts("+nbLine+");");
        jdbcTemplate.execute("CALL generate_random_depenses_ticket("+nbLine+");");
        jdbcTemplate.execute("CALL generate_random_depenses_lead("+nbLine+");");
    }
}
