package app.feature;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
public class FeatureController {

    @Autowired
    private FeatureDao featureDao;

    @GetMapping("/feature")
    public List<Feature> index() {
        return featureDao.getFeatures();
    }
}