package playground.neo4j.config;

import org.apache.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mapping.model.MappingException;
import org.springframework.data.neo4j.annotation.NodeEntity;
import org.springframework.data.neo4j.support.Neo4jTemplate;
import org.springframework.stereotype.Component;
import playground.neo4j.domain.BaseObject;

/**
 * source: http://stackoverflow.com/questions/8852491/lazy-eager-loading-fetching-in-neo4j-spring-data
 */
@Component
@Aspect
public class Neo4jFetchAspect implements InitializingBean {

    private final static Logger logger = Logger.getLogger( Neo4jFetchAspect.class );

    @Autowired
    private Neo4jTemplate template;

    @Override
    public void afterPropertiesSet() throws Exception {
        logger.info( "Neo4jFetchAspect initialised" );
        assert (template != null);
    }

    @Pointcut( "execution(* playground.neo4j.domain.*.get*(..))" )
    public void modelGetter() {
    }

    @Around( "modelGetter()" )
    public Object autoFetch( ProceedingJoinPoint pjp ) throws Throwable {
        logger.debug( ">>>>>>> Neo4jFetchAspect.autoFetch: " + pjp.getSignature().toShortString() );
        Object o = pjp.proceed();
        if ( o != null ) {
            if ( o.getClass().isAnnotationPresent( NodeEntity.class ) ) {
                logger.debug( "\t NodeEntity > " + o.getClass() );
                if ( o instanceof BaseObject ) {
                    logger.debug( "\t BaseObject > " + o.getClass() );
                    BaseObject bo = (BaseObject) o;
                    if ( ! bo.isFetched() ) {
                        logger.debug( "\t FETCHING " + o.getClass().getSimpleName() );
                        return template.fetch( o );
                    }
                    return o;
                }
                try {
                    return template.fetch( o );
                } catch ( MappingException me ) {
                    me.printStackTrace();
                }
            }
        }
        logger.debug( "\t NO FETCH " + o.getClass().getSimpleName() );
        return o;
    }
}
