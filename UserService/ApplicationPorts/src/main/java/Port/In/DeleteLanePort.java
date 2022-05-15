package Port.In;

import exceptions.ItemNotFound;
import model.Lane;

import java.util.UUID;

public interface DeleteLanePort {
    Lane delete(UUID uuid) throws ItemNotFound;
}
