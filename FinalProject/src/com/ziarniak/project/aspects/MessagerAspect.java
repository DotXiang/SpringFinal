package com.ziarniak.project.aspects;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

import com.ziarniak.project.models.Game;
import com.ziarniak.project.service.GameService;

@Aspect
public class MessagerAspect {
	
	
	
	@Pointcut("execution(* com.ziarniak.project.service.GameService+.*addGame*(..))")
	public void pointcutAddingGame(){}
	
	@Pointcut("execution(* com.ziarniak.project.dao.GameDAO.getAllGames(..))")
	public void pointcutReadingGames(){}
	
		
	@Around("pointcutAddingGame() && args(game)")
	public Object aroundAddingGame(ProceedingJoinPoint point,Game game) throws Throwable,ConstraintViolationException{
		
		
		GameService service = (GameService) point.getTarget();
        
        Set<ConstraintViolation<Game>> constraintViolations =
                service.getValidator().validate(game);
        if(!constraintViolations.isEmpty()){
        
        throw new ConstraintViolationException(constraintViolations);
        }else{
        
        	try {
        		 
        		 Object ret= point.proceed();
        		 System.out.println(ret.toString());
    			System.out.println("Game added : "+game);
    			
    		} catch (Throwable t) {
    			System.out.println("Error "+ t.getMessage());
    		}
        }
        	
        
		return constraintViolations;
	}
}
