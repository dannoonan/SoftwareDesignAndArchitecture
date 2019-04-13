package ie.demo.service.impl;

import ie.demo.service.AbstractUserFactory;
import org.springframework.stereotype.Service;

@Service
public class FactoryProvider {
    public static AbstractUserFactory getFactory(int choice){

        if(choice == 1 || choice == 2) {
            return new CustomerUserFactory();
        }
        else if(choice == 3 || choice == 4) {
            return new AdminUserFactory();
        }

        return null;
    }
}
