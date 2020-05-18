package service;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import entities.Vehicle;

@RequestScoped
@ManagedBean(name = "BasicView")
public class BasicView  {
     
    private List<Vehicle> listVehicles;
    
     
    public List<Vehicle> getCars() {
        return listVehicles;
    }
 
}