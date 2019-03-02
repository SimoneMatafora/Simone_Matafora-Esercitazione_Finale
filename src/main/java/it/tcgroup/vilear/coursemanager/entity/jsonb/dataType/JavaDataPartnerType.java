package it.tcgroup.vilear.coursemanager.entity.jsonb.dataType;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import it.tcgroup.vilear.coursemanager.common.hibernate.JsonDataType;
import it.tcgroup.vilear.coursemanager.entity.PartnerEntity;
import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class JavaDataPartnerType extends JsonDataType {

    @Override
    public Object nullSafeGet(final ResultSet rs, final String[] names, final SharedSessionContractImplementor session,
                              final Object owner) throws HibernateException, SQLException {
        final String cellContent = rs.getString(names[0]);
        if (cellContent == null) {
            return null;
        }
        try {
            final ObjectMapper mapper = new ObjectMapper();
            return mapper.readValue(cellContent.getBytes("UTF-8"), new TypeReference<List<PartnerEntity>>() {});
        } catch (final Exception ex) {
            throw new RuntimeException("Failed to convert String to Contact: " + ex.getMessage(), ex);
        }
    }
}
