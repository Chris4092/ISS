package domain;

public class Reservation extends Entity<Long>{
    private Long idClient;
    private Long idShow;
    private Integer numberOfTickets;
    private Integer discount;
    private Integer price;

    public Reservation() {
    }

    public Reservation(Long idClient, Long idShow, Integer numberOfTickets, Integer discount, Integer price) {
        this.idClient = idClient;
        this.idShow = idShow;
        this.numberOfTickets = numberOfTickets;
        this.discount = discount;
        this.price = price;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Long getIdClient() {
        return idClient;
    }

    public void setIdClient(Long idClient) {
        this.idClient = idClient;
    }

    public Long getIdShow() {
        return idShow;
    }

    public void setIdShow(Long idShow) {
        this.idShow = idShow;
    }

    public Integer getNumberOfTickets() {
        return numberOfTickets;
    }

    public void setNumberOfTickets(Integer numberOfTickets) {
        this.numberOfTickets = numberOfTickets;
    }

    public Integer getDiscount() {
        return discount;
    }

    public void setDiscount(Integer discount) {
        this.discount = discount;
    }
}
