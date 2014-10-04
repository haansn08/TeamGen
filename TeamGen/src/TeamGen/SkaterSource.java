package TeamGen;

import java.util.List;

/**
 * Created by Stefan Haan on 9/27/14.
 */
public interface SkaterSource {
    public List<Skater> readAllSkaters() throws Exception;
}
