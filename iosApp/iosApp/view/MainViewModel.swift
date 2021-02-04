//
//  MainViewModel.swift
//  iosApp
//
//  Created by Héctor Santiago Coronel on 03/02/2021.
//  Copyright © 2021 orgName. All rights reserved.
//

import Foundation
import shared
import RxSwift

class MainViewModel {
    
    var randomNumberUseCase : RandomNumberUseCase
    var loginUseCase : LoginUseCase
    
    init(randomNumberUseCase : RandomNumberUseCase,
         loginUseCase : LoginUseCase) {
        self.randomNumberUseCase = randomNumberUseCase
        self.loginUseCase = loginUseCase
    }
    
    //business
    private let randomNumber = PublishSubject<Int>()
    private let userProfile  = PublishSubject<UserProfile>()
    
    //common
    private let loading = PublishSubject<Bool>()
    private let genericError = PublishSubject<Bool>()
    
    func getRandomNumber() -> PublishSubject<Int> {
        return randomNumber
    }
    
    func getUserProfile() -> PublishSubject<UserProfile>{
        return userProfile
    }
    
    func getLoading() -> PublishSubject<Bool> {
        return loading
    }
    
    func getGenericError() -> PublishSubject<Bool>{
        return genericError
    }
    
    func executeRandomNumber(){
        self.loading.onNext(true)
        self.randomNumberUseCase.executeByJob{ number in
            guard let number : Int = Int(number.description) else {
                self.genericError.onNext(true)
                return
            }
            self.randomNumber.onNext(number)
        } completionHandler: { (unit :KotlinUnit?, error :Error?) in
            self.loading.onNext(false)
        }
    }
    
    func executeLogin(){
        self.loading.onNext(true)
        self.loginUseCase.bind(email: "elonmusk@tesla.com", encryptedPassword: "HkiNj2tEmCUSanSjGsYkOg==", device: DeviceDummy.init().getAndroid())
        do {
            try self.loginUseCase.executeByJob{ userProfile in
                self.userProfile.onNext(userProfile)
                self.loading.onNext(false)
            }
        }catch{
            self.genericError.onNext(true)
            self.loading.onNext(false)
        }
    }
    
}
