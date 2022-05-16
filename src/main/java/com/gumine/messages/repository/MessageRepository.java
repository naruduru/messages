package com.gumine.messages.repository;

import com.gumine.messages.vo.Message;
import org.apache.juli.logging.Log;
import org.apache.juli.logging.LogFactory;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.*;

@Component
public class MessageRepository {
    private final static Log log = LogFactory.getLog(MessageRepository.class);

    private DataSource dataSource;

    public MessageRepository(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public Message saveMessage(Message message) {
        Connection c = DataSourceUtils.getConnection(dataSource);

        try {
            String insertSql = "INSERT INTO planlist" +
                    " (plan_id, plan_title, str_date, end_date, member, ins_date, ins_id, upd_date, upd_id, del_yn)" +
                    " VALUES('999', '1', '20220501', '20220530', '[]', now(), 'leemg', now(), 'leemg', 'N');";
            PreparedStatement ps = c.prepareStatement(insertSql, Statement.RETURN_GENERATED_KEYS);
            // SQL에 필요한 매개변수를 준비한다.
            int rowsAffected = ps.executeUpdate();

            if (rowsAffected > 0) {
                // 새로 저장된 메시지 id 가져오기
                ResultSet result = ps.getGeneratedKeys();
                if (result.next()) {
                    int id = result.getInt(1);
                    return new Message(id, message.getText(), message.getCreateDate());
                } else {
                    log.error("Failed to retrieve id. No row in result set");
                    return null;
                }
            } else {
                // Insert 실패
                return null;
            }
        } catch (SQLException ex) {
            log.error("Failed to save message", ex);

            try {
                c.close();
            } catch (SQLException e) {
                log.error("Failed to close connection", e);
            } finally {
                DataSourceUtils.releaseConnection(c, dataSource);
            }
        }
        // 데이터베이스에 메시지를 저장한다.
        log.info("Saved message: " + message.getText());

        return null;
    }
}
