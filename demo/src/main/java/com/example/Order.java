package com.example;

import java.util.Arrays;
import java.util.List;

public class Order {
    private int id;
    private String customerName;
    private double amount;
    private String status = "NEW";

    Order(int id, String customerName, double amount) {
        this.id = id;
        this.customerName = customerName;
        this.amount = amount;
    }

    public String getCustomerName() {
        return customerName;
    }

    public String getStatus() {
        return status;
    }

    public double getAmount() {
        return amount;
    }

    public void pay() {
        if (status.equals("PAID") || status.equals("CANCELLED")) {
            throw new IllegalStateException("Status incorrect");
        }
        status = "PAID";
    }

    public void cancel() {
        if (status.equals("PAID") || status.equals("CANCELLED")) {
            throw new IllegalStateException("Status incorrect");
        }
        status = "CANCELLED";
    };

    @Override
    public String toString() {
        return "Order{Id=" + id + ", customer='" + customerName + "', amount=" + amount + ", status='"
                + status + "'}";
    };
}

class OrderTest1 {
    public static void main(String[] args) {
        Order o1 = new Order(1, "AnnA", 100);
        Order o2 = new Order(2, "John", 33.3);
        Order o3 = new Order(3, "Sam", 15.1);
        Order o4 = new Order(4, "Nina", 67);
        Order o5 = new Order(5, "Liza", 32.05);
        List<Order> orders = Arrays.asList(o1, o2, o3, o4, o5);
        o1.pay();
        try {
            o1.pay();
        } catch (IllegalStateException e) {
            System.out.println("Status incorrect");
        }
        o2.pay();
        o3.pay();
        o4.cancel();

        // double amm = 0;
        // int count = 0;
        // for (int i = 0; i < Orders.size(); i++) {
        // if (Orders.get(i).status.equals("PAID")) {
        // count = count + 1;
        // amm = amm + Orders.get(i).amount;
        // }
        // if (Orders.get(i).status.equals("CANCELLED")) {
        // System.out.println(Orders.get(i).customerName);
        // }
        // }
        // System.out.println("Total paid:" + count + " Total amount:" + amm);

        long paidCount = orders.stream().filter(o -> o.getStatus().equals("PAID")).count();
        double totalPaid = orders.stream().filter(o -> o.getStatus().equals("PAID")).mapToDouble(o -> o.getAmount())
                .sum();
        orders.stream().filter(o -> o.getStatus().equals("CANCELLED"))
                .forEach(o -> System.out.println("Customer name:" + o.getCustomerName()));
        System.out.println(orders);
        System.out.println("Total paid:" + paidCount + " Total amount:" + totalPaid);

    }
}