/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.demo.Servicio;

import com.example.demo.Modelo.Cloud;
import com.example.demo.Repositorio.CloudRepositorio;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author USUARIO
 */
@Service

public class CloudServicio {
       @Autowired
    private CloudRepositorio cloudRepositorio;

    public List<Cloud> getAll(){
        return cloudRepositorio.getAll();
    }

    public Optional<Cloud> getCloud(int cloudId) {
        return cloudRepositorio.getCloud(cloudId);
    }

    public Cloud save(Cloud cloud){
        if(cloud.getId()==null){
            return cloudRepositorio.save(cloud);
        }else{
            Optional<Cloud>e= cloudRepositorio.getCloud(cloud.getId());
            if(e.isEmpty()){
                return cloudRepositorio.save(cloud);
            }else{
                return cloud;
            }
        }
    }
    
       public Cloud update(Cloud cloud){
        if(cloud.getId()!=null){
            Optional<Cloud> e= cloudRepositorio.getCloud(cloud.getId());
            if(!e.isEmpty()){
                if(cloud.getName()!=null){
                    e.get().setName(cloud.getName());
                }
                if(cloud.getBrand()!=null){
                    e.get().setBrand(cloud.getBrand());
                }
                if(cloud.getYear()!=null){
                    e.get().setYear(cloud.getYear());
                }
                if(cloud.getDescription()!=null){
                    e.get().setDescription(cloud.getDescription());
                }
                if(cloud.getCategory()!=null){
                    e.get().setCategory(cloud.getCategory());
                }
                cloudRepositorio.save(e.get());
                return e.get();
            }else{
                return cloud;
            }
        }else{
            return cloud;
        }
    }

    
    
      public boolean deleteCloud (int id){
        Boolean d = getCloud(id).map(cloud -> {
            cloudRepositorio.delete(cloud);
            return true;
        }).orElse(false);
        return d;
    }
    
    
}
