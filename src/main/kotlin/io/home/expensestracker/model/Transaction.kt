package io.home.expensestracker.model;
import com.fasterxml.jackson.annotation.JsonFormat
import org.springframework.format.annotation.DateTimeFormat
import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDate;
import javax.persistence.*

enum class TransactionType {
  CREDIT,
  DEBIT;
  override fun toString() = super.toString().toLowerCase()
}

@Entity
@Table(name="transactions")
data class Transaction(
  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(columnDefinition = "serial")
  var id: Long? = null,
  val description: String,
  @Column(name="extended_info")
  var extendedInfo : String? = null,
  val amount: BigDecimal,
  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
  val date: LocalDate = LocalDate.now(),
  @Column(name="type",columnDefinition="varchar")
  @Enumerated(EnumType.STRING)
  val transactionType: TransactionType = TransactionType.DEBIT,
  @ManyToOne
  @JoinColumn(name = "category_id",columnDefinition = "int4")
  var category: Category?=null
)