package com.pollub.chmury;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.Hashtable;

@RestController
public class FibonacciEndpoints {
    private Hashtable<Integer,Long> calculatedValues = new Hashtable<Integer, Long>();

    private final Double sqrOfFive = Math.sqrt(5);
    private final Double nominatorPositiveConst = (1 + sqrOfFive)/2 ;

    private final Double nominatorNegatConst = (1 - sqrOfFive)/2 ;


    @PostMapping("/calculate")
    Long getNFibNumber(@RequestBody Integer nFibNumber){
        if(calculatedValues.containsKey(nFibNumber))
            return calculatedValues.get(nFibNumber);

        Double nominatorRes = (Math.pow(nominatorPositiveConst, nFibNumber) - Math.pow(nominatorNegatConst, nFibNumber));
        Long result = (long) (nominatorRes / sqrOfFive);
        calculatedValues.put(nFibNumber,result);

        return result;
    }


    @RequestMapping("/")
    public ModelAndView mainPage()
    {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("index");
        return modelAndView;
    }
}
