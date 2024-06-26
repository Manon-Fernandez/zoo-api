package com.zoo_arcadia.service;

import com.zoo_arcadia.pojo.Horaire;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.zoo_arcadia.repository.HoraireRepository;

import java.util.List;
import java.util.Optional;

@Service
public class HoraireService {

    @Autowired
    private HoraireRepository horaireRepository;

    public List<Horaire> getAllHoraires() {return this.horaireRepository.findAll();}

    public void deleteHoraireById(Long id) {this.horaireRepository.deleteById(id);}

    public void updateHoraire(Long id, Horaire horaire) {
        Optional<Horaire> unHoraire = this.horaireRepository.findById(id);
        if (unHoraire.isPresent()) {
            Horaire horaireToSave = unHoraire.get();
            horaireToSave.setJour(horaire.getJour());
            horaireToSave.setHeure_ouverture(horaire.getHeure_ouverture());
            horaireToSave.setHeure_fermeture(horaire.getHeure_fermeture());
            this.horaireRepository.save(horaireToSave);
        }
    }

    public void createHoraire(Horaire horaire) {
        Optional<Horaire> unHoraire = this.horaireRepository.findHoraireByJour(horaire.getJour());
        if (unHoraire.isPresent()){
            throw new RuntimeException("Un horaire existe déjà pour ce jour");
        } else {
            this.horaireRepository.save(horaire);
        }
    }

}
