package kz.orders.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import kz.orders.app.entity.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long>{
	
	// получаем сущности Order с загрузкой данных из таблицы курьерской службы
	// с флагом не прочитано
	@Query("SELECT o FROM Order o "
			+ "JOIN FETCH o.courierOrder c "
			+ "WHERE c.readed = false AND o.status = kz.orders.app.entity.Status.PROCESSING")
	public List<Order> findByCourierStatusChange();
	
}
