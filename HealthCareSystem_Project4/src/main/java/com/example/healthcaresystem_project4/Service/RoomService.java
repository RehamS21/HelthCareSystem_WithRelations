package com.example.healthcaresystem_project4.Service;

import com.example.healthcaresystem_project4.Api.ApiException;
import com.example.healthcaresystem_project4.Model.Patient;
import com.example.healthcaresystem_project4.Model.Room;
import com.example.healthcaresystem_project4.Repository.PatientRepository;
import com.example.healthcaresystem_project4.Repository.RoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RoomService {
    private final RoomRepository roomRepository;
    private final PatientRepository patientRepository;

    public List<Room> getAllRoom(){
        return roomRepository.findAll();
    }

    public void addRoom(Room room){
        Patient p = patientRepository.findPatientById(room.getPatientId());
        if (p == null)
            throw new ApiException("Patient id is wrong");
        roomRepository.save(room);
    }

    public void updateRoom(Integer id, Room room){
        Room oldRoom = roomRepository.findRoomById(id);

        if (oldRoom == null)
            throw new ApiException("Sorry, room id is wrong");

        Patient p = patientRepository.findPatientById(oldRoom.getPatientId());
        if (p==null)
            throw new ApiException("Sorry the patient id is wrong");

        oldRoom.setRoomtype(room.getRoomtype());
        oldRoom.setPatientId(room.getPatientId());

        roomRepository.save(oldRoom);
    }

    public void deleteRoom(Integer id){
        Room deleteRoom = roomRepository.findRoomById(id);

        if (deleteRoom == null)
            throw new ApiException("Sorry, room id is wrong");

        roomRepository.delete(deleteRoom);
    }

    public List<Room> getBasedOnRoomType(String roomtype){
        List<Room> roomList = roomRepository.basedOnRoomType(roomtype);

        if (roomList.isEmpty())
            throw new ApiException("Sorry the room type is wrong");

        return roomList;

    }

    public List<Room> orderedRooms(){
        List<Room> roomList = roomRepository.orderedRoom();

        return roomList;
    }
}
