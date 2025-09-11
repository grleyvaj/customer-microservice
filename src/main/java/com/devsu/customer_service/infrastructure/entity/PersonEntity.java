package com.devsu.customer_service.infrastructure.entity;

import com.devsu.customer_service.domain.helpers.Generator;
import com.devsu.customer_service.domain.models.Gender;
import jakarta.persistence.*;
import lombok.Data;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

@Accessors(chain = true)
@Data
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "persons")
public abstract class PersonEntity {

	@Id
	@Column(name = "id", length = 26)
	private String id;

	@Column(name = "name", length = 100, nullable = false)
	private String name;

	@Column(name = "gender", nullable = false)
	@Enumerated(EnumType.STRING)
	private Gender gender;

	@Column(name = "age", nullable = false)
	private int age;

	@Column(name = "address", length = 255, nullable = false)
	private String address;

	@Column(name = "phone", length = 100, nullable = false)
	private String phone;

	@Column(name = "is_active", nullable = false)
	private Boolean isActive = false;

	@Column(name = "is_deleted")
	private Boolean isDeleted = false;

	@Column(name = "created_at", nullable = false, updatable = false)
	private LocalDateTime createdAt;

	@Column(name = "updated_at")
	private LocalDateTime updatedAt;

	@Column(name = "deleted_at")
	private LocalDateTime deletedAt;

	@PrePersist
	protected void onCreate() {
		if(this.id == null) {
			this.id = Generator.ulid();
		}
	}

}
