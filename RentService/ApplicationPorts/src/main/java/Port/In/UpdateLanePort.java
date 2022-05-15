package Port.In;

import exceptions.ItemNotFound;
import model.Lane;

public interface UpdateLanePort {
    Lane update(Lane lane) throws ItemNotFound;
}
