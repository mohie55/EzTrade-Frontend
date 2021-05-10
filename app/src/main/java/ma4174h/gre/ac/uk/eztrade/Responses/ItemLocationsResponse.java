package ma4174h.gre.ac.uk.eztrade.Responses;

import java.util.List;

import ma4174h.gre.ac.uk.eztrade.DTO.ItemLocationsDTO;

public class ItemLocationsResponse {

    private List<ItemLocationsDTO> itemLocationsDTOList;
    private String message;

    public ItemLocationsResponse(List<ItemLocationsDTO> itemLocationsDTOList, String message) {
        this.itemLocationsDTOList = itemLocationsDTOList;
        this.message = message;
    }

    public List<ItemLocationsDTO> getItemLocationsDTOList() {
        return itemLocationsDTOList;
    }

    public void setItemLocationsDTOList(List<ItemLocationsDTO> itemLocationsDTOList) {
        this.itemLocationsDTOList = itemLocationsDTOList;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
