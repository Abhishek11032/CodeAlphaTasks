import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

class Hotel {
    private String name;
    private double rating;
    private ArrayList<Review> reviews;

    public Hotel(String name) {
        this.name = name;
        this.rating = 0.0;
        this.reviews = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public double getRating() {
        return rating;
    }

    public void addReview(Review review) {
        reviews.add(review);
        updateRating();
    }

    private void updateRating() {
        double totalRating = 0.0;
        for (Review review : reviews) {
            totalRating += review.getRating();
        }
        rating = totalRating / reviews.size();
    }

    public ArrayList<Review> getReviews() {
        return reviews;
    }
}

class Review {
    private String user;
    private double rating;
    private String comment;

    public Review(String user, double rating, String comment) {
        this.user = user;
        this.rating = rating;
        this.comment = comment;
    }

    public double getRating() {
        return rating;
    }

    @Override
    public String toString() {
        return "User: " + user + "\nRating: " + rating + "\nComment: " + comment + "\n";
    }
}

public class HotelReviewSystem {
    private static ArrayList<Hotel> hotels = new ArrayList<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        
        hotels.add(new Hotel("Hotel A"));
        hotels.add(new Hotel("Hotel B"));

        while (true) {
            System.out.println("1. View Hotels");
            System.out.println("2. Add Review");
            System.out.println("3. Sort and Filter Reviews");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); 

            switch (choice) {
                case 1:
                    viewHotels();
                    break;
                case 2:
                    addReview();
                    break;
                case 3:
                    sortAndFilterReviews();
                    break;
                case 4:
                    System.out.println("Exiting...");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void viewHotels() {
        System.out.println("Hotels:");
        for (Hotel hotel : hotels) {
            System.out.println(hotel.getName() + " - Rating: " + hotel.getRating());
        }
    }

    private static void addReview() {
        System.out.println("Select a hotel to review:");
        for (int i = 0; i < hotels.size(); i++) {
            System.out.println((i + 1) + ". " + hotels.get(i).getName());
        }

        int hotelIndex = new Scanner(System.in).nextInt() - 1;
        if (hotelIndex < 0 || hotelIndex >= hotels.size()) {
            System.out.println("Invalid hotel selection.");
            return;
        }

        System.out.print("Enter your name: ");
        String user = new Scanner(System.in).nextLine();

        System.out.print("Enter your rating (1.0 - 5.0): ");
        double rating = new Scanner(System.in).nextDouble();

        System.out.print("Enter your comment: ");
        String comment = new Scanner(System.in).nextLine();

        Review review = new Review(user, rating, comment);
        hotels.get(hotelIndex).addReview(review);

        System.out.println("Review added successfully!");
    }

    private static void sortAndFilterReviews() {
        System.out.println("Select a hotel to view reviews:");
        for (int i = 0; i < hotels.size(); i++) {
            System.out.println((i + 1) + ". " + hotels.get(i).getName());
        }

        int hotelIndex = new Scanner(System.in).nextInt() - 1;
        if (hotelIndex < 0 || hotelIndex >= hotels.size()) {
            System.out.println("Invalid hotel selection.");
            return;
        }

        ArrayList<Review> reviews = hotels.get(hotelIndex).getReviews();

        System.out.println("Sort by:");
        System.out.println("1. Rating (High to Low)");
        System.out.println("2. Rating (Low to High)");
        System.out.println("3. Back");

        int sortBy = new Scanner(System.in).nextInt();
        switch (sortBy) {
            case 1:
                Collections.sort(reviews, Comparator.comparing(Review::getRating).reversed());
                break;
            case 2:
                Collections.sort(reviews, Comparator.comparing(Review::getRating));
                break;
            case 3:
                return;
            default:
                System.out.println("Invalid choice. Returning to main menu.");
                return;
        }

        System.out.println("Filtered Reviews:");
        for (Review review : reviews) {
            System.out.println(review);
        }
    }
}
