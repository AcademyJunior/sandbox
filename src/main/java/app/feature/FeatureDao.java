package app.feature;

import org.jooq.DSLContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

import static org.jooq.util.maven.example.Tables.FEATURES;

@Repository
public class FeatureDao {

    @Autowired
    private DSLContext dslContext;

    public List<Feature> getFeatures(){
        return dslContext.selectFrom(FEATURES).fetchInto(Feature.class);
    }
}
