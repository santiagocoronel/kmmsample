//
//  MainViewController.swift
//  iosApp
//
//  Created by Héctor Santiago Coronel on 03/02/2021.
//  Copyright © 2021 orgName. All rights reserved.
//

import Foundation
import UIKit
import shared
import RxSwift

class MainViewController: UIViewController {
    
    
    @IBOutlet weak var labelDeviceInformation: UILabel!
    @IBOutlet weak var labelRandomNumber: UILabel!
    @IBOutlet weak var buttonRandomNumber: UIButton!
    @IBOutlet weak var labelFirstName: UILabel!
    @IBOutlet weak var labelLastName: UILabel!
    @IBOutlet weak var labelNickName: UILabel!
    @IBOutlet weak var labelEmail: UILabel!
    @IBOutlet weak var buttonLogin: UIButton!
    
    private var subscriptionRandomNumber = DisposeBag()
    private var subscriptionUserProfile = DisposeBag()
    
    var mainViewModel : MainViewModel
    
    required init?(coder aDecoder: NSCoder){
        
        let randomNumberRepository = RandomNumberRepository()
        let randomNumberUseCase = RandomNumberUseCase(repository: randomNumberRepository)
        
        let api = Api()
        let loginRepository = LoginRepository(api : api)
        let loginUseCase = LoginUseCase(repository: loginRepository)
        
        
        self.mainViewModel = MainViewModel(randomNumberUseCase: randomNumberUseCase , loginUseCase: loginUseCase)
        super.init(coder: aDecoder)
    }
    
    override func viewDidLoad() {
        super.viewDidLoad()
        
        labelDeviceInformation.text = Greeting.init().greeting()
        
        mainViewModel.getRandomNumber().subscribe (onNext:{ number in
            self.showRandomNumber(number: number)
        })
        
        mainViewModel.getUserProfile().subscribe(onNext:{ userProfile in
            self.showProfileInformation(userProfile: userProfile)
        })
        
    }
    
    
    @IBAction func onClickRandomNumber(_ sender: Any) {
        mainViewModel.executeRandomNumber()
    }
    
    
    @IBAction func onClickLogin(_ sender: Any) {
        mainViewModel.executeLogin()
    }
    
    func showRandomNumber(number : Int){
        self.labelRandomNumber.text = String(describing: number)
    }
    
    func showProfileInformation(userProfile : UserProfile){
        labelFirstName.text = userProfile.firstname
        labelLastName.text = userProfile.lastname
        labelNickName.text = userProfile.nick
        labelEmail.text = userProfile.email
    }
}
