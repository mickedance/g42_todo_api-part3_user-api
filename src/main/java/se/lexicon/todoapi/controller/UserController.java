package se.lexicon.todoapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import se.lexicon.todoapi.exception.ObjectNotFoundException;
import se.lexicon.todoapi.model.dto.UserDto;
import se.lexicon.todoapi.service.UserService;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {

    UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }


    @PostMapping()
    public ResponseEntity<UserDto> register(@RequestBody UserDto dto){
        System.out.println("dto = " + dto);
        UserDto result = null;
        try {
            result = userService.register(dto);
            return ResponseEntity.status(HttpStatus.CREATED).body(result);

        } catch (ObjectNotFoundException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();

        }

    }


    @GetMapping("/{username}")
    public ResponseEntity<UserDto> findByUsername(@PathVariable("username") String username){
        // todo: implement rest full api for find by username
        try{
            UserDto userDto = userService.findByUsername(username);
            return ResponseEntity.status(HttpStatus.FOUND).body(userDto);
        }catch (ObjectNotFoundException e){
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
    }


    @PutMapping("/disable/{username}")
    public ResponseEntity<Void> disableUser(@PathVariable("username") String username){
        // todo: implement disable user by username api
        userService.disableUserByUsername(username);
        return ResponseEntity.ok().build();
    }


    @PutMapping("/enable/{username}")
    public ResponseEntity<Void> enableUser(@PathVariable("username") String username){
        // todo: implement enable user by username api
        userService.enableUserByUsername(username);
        return ResponseEntity.ok().build();
    }

}
