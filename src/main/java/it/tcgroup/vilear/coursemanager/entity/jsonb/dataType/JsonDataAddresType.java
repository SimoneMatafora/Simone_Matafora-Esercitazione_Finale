package it.tcgroup.vilear.coursemanager.entity.jsonb.dataType;

import com.fasterxml.jackson.databind.ObjectMapper;
import it.tcgroup.vilear.coursemanager.common.hibernate.JsonDataType;
import it.tcgroup.vilear.coursemanager.entity.jsonb.Address;
import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;

import java.sql.ResultSet;
import java.sql.SQLException;

public class JsonDataAddresType extends JsonDataType {

    @Override
    public Object nullSafeGet(final ResultSet rs, final String[] names, final SharedSessionContractImplementor session,
                              final Object owner) throws HibernateException, SQLException {
        final String cellContent = rs.getString(names[0]);
        if (cellContent == null) {
            return null;
        }
        try {
            final ObjectMapper mapper = new ObjectMapper();
            return mapper.readValue(cellContent.getBytes("UTF-8"), Address.class);
        } catch (final Exception ex) {
            throw new RuntimeException("Failed to convert String to Contact: " + ex.getMessage(), ex);
        }
    }
}
