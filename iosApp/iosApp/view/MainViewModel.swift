//
//  MainViewModel.swift
//  iosApp
//
//  Created by Héctor Santiago Coronel on 03/02/2021.
//  Copyright © 2021 orgName. All rights reserved.
//

import Foundation
import shared

class MainViewModel : ObservableObject {
    
    var randomNumberUseCase : RandomNumberUseCase
    var loginUseCase : LoginUseCase
    
    init(randomNumberUseCase : RandomNumberUseCase,
         loginUseCase : LoginUseCase) {
        self.randomNumberUseCase = randomNumberUseCase
        self.loginUseCase = loginUseCase
    }
    
    //business
    @Published private var randomNumber : Int = 0
    @Published private var userProfile : UserProfile? = nil
    
    //common
    @Published private var loading : Bool = false
    @Published private var genericError : Bool = false
    
    func getRandomNumber() -> Int {
        return randomNumber
    }
    
    func getUserProfile() -> UserProfile? {
        return userProfile
    }
    
    func getLoading() -> Bool {
        return loading
    }
    
    func getGenericError() -> Bool{
        return genericError
    }
    
    func executeRandomNumber(){
        self.loading = true
        self.randomNumberUseCase.executeByJob{ number in
            guard let number : Int = Int(number.description) else {
                self.genericError = true
                return
            }
            self.randomNumber = number
        } completionHandler: { (unit :KotlinUnit?, error :Error?) in
            self.loading = false
        }
    }
    
    func executeLogin(){
        self.loading = true
        self.loginUseCase.bind(email: "drogocoronel@hotmail.com", encryptedPassword: "HkiNj2tEmCUSanSjGsYkOg==", device: DeviceDummy.init().getAndroid())
        do {
            try self.loginUseCase.executeByJob{ userProfile in
                self.userProfile = userProfile
                self.loading = false
            }
        }catch{
            self.genericError  = true
            self.loading = false
        }
    }
    
}
