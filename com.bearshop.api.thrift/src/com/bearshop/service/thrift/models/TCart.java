/**
 * Autogenerated by Thrift Compiler (0.9.1)
 *
 * DO NOT EDIT UNLESS YOU ARE SURE THAT YOU KNOW WHAT YOU ARE DOING
 *  @generated
 */
package com.bear.shop.service.api.thrift.models;

import org.apache.thrift.scheme.IScheme;
import org.apache.thrift.scheme.SchemeFactory;
import org.apache.thrift.scheme.StandardScheme;

import org.apache.thrift.scheme.TupleScheme;
import org.apache.thrift.protocol.TTupleProtocol;
import org.apache.thrift.protocol.TProtocolException;
import org.apache.thrift.EncodingUtils;
import org.apache.thrift.TException;
import org.apache.thrift.async.AsyncMethodCallback;
import org.apache.thrift.server.AbstractNonblockingServer.*;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.util.EnumMap;
import java.util.Set;
import java.util.HashSet;
import java.util.EnumSet;
import java.util.Collections;
import java.util.BitSet;
import java.nio.ByteBuffer;
import java.util.Arrays;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TCart implements org.apache.thrift.TBase<TCart, TCart._Fields>, java.io.Serializable, Cloneable, Comparable<TCart> {
  private static final org.apache.thrift.protocol.TStruct STRUCT_DESC = new org.apache.thrift.protocol.TStruct("TCart");

  private static final org.apache.thrift.protocol.TField ID_FIELD_DESC = new org.apache.thrift.protocol.TField("id", org.apache.thrift.protocol.TType.I32, (short)1);
  private static final org.apache.thrift.protocol.TField PRODUCT_ID_FIELD_DESC = new org.apache.thrift.protocol.TField("productId", org.apache.thrift.protocol.TType.I32, (short)2);
  private static final org.apache.thrift.protocol.TField COLORS_FIELD_DESC = new org.apache.thrift.protocol.TField("colors", org.apache.thrift.protocol.TType.STRING, (short)3);
  private static final org.apache.thrift.protocol.TField SIZES_FIELD_DESC = new org.apache.thrift.protocol.TField("sizes", org.apache.thrift.protocol.TType.STRING, (short)4);
  private static final org.apache.thrift.protocol.TField QUANTITY_FIELD_DESC = new org.apache.thrift.protocol.TField("quantity", org.apache.thrift.protocol.TType.I32, (short)5);
  private static final org.apache.thrift.protocol.TField STATUS_FIELD_DESC = new org.apache.thrift.protocol.TField("status", org.apache.thrift.protocol.TType.I32, (short)6);
  private static final org.apache.thrift.protocol.TField CREATED_AT_FIELD_DESC = new org.apache.thrift.protocol.TField("createdAt", org.apache.thrift.protocol.TType.STRING, (short)7);
  private static final org.apache.thrift.protocol.TField MODIFIED_AT_FIELD_DESC = new org.apache.thrift.protocol.TField("modifiedAt", org.apache.thrift.protocol.TType.STRING, (short)8);
  private static final org.apache.thrift.protocol.TField USER_CREATED_FIELD_DESC = new org.apache.thrift.protocol.TField("userCreated", org.apache.thrift.protocol.TType.I32, (short)9);
  private static final org.apache.thrift.protocol.TField USER_MODIFIED_FIELD_DESC = new org.apache.thrift.protocol.TField("userModified", org.apache.thrift.protocol.TType.I32, (short)10);

  private static final Map<Class<? extends IScheme>, SchemeFactory> schemes = new HashMap<Class<? extends IScheme>, SchemeFactory>();
  static {
    schemes.put(StandardScheme.class, new TCartStandardSchemeFactory());
    schemes.put(TupleScheme.class, new TCartTupleSchemeFactory());
  }

  public int id; // required
  public int productId; // required
  public String colors; // required
  public String sizes; // required
  public int quantity; // required
  public int status; // required
  public String createdAt; // required
  public String modifiedAt; // required
  public int userCreated; // required
  public int userModified; // required

  /** The set of fields this struct contains, along with convenience methods for finding and manipulating them. */
  public enum _Fields implements org.apache.thrift.TFieldIdEnum {
    ID((short)1, "id"),
    PRODUCT_ID((short)2, "productId"),
    COLORS((short)3, "colors"),
    SIZES((short)4, "sizes"),
    QUANTITY((short)5, "quantity"),
    STATUS((short)6, "status"),
    CREATED_AT((short)7, "createdAt"),
    MODIFIED_AT((short)8, "modifiedAt"),
    USER_CREATED((short)9, "userCreated"),
    USER_MODIFIED((short)10, "userModified");

    private static final Map<String, _Fields> byName = new HashMap<String, _Fields>();

    static {
      for (_Fields field : EnumSet.allOf(_Fields.class)) {
        byName.put(field.getFieldName(), field);
      }
    }

    /**
     * Find the _Fields constant that matches fieldId, or null if its not found.
     */
    public static _Fields findByThriftId(int fieldId) {
      switch(fieldId) {
        case 1: // ID
          return ID;
        case 2: // PRODUCT_ID
          return PRODUCT_ID;
        case 3: // COLORS
          return COLORS;
        case 4: // SIZES
          return SIZES;
        case 5: // QUANTITY
          return QUANTITY;
        case 6: // STATUS
          return STATUS;
        case 7: // CREATED_AT
          return CREATED_AT;
        case 8: // MODIFIED_AT
          return MODIFIED_AT;
        case 9: // USER_CREATED
          return USER_CREATED;
        case 10: // USER_MODIFIED
          return USER_MODIFIED;
        default:
          return null;
      }
    }

    /**
     * Find the _Fields constant that matches fieldId, throwing an exception
     * if it is not found.
     */
    public static _Fields findByThriftIdOrThrow(int fieldId) {
      _Fields fields = findByThriftId(fieldId);
      if (fields == null) throw new IllegalArgumentException("Field " + fieldId + " doesn't exist!");
      return fields;
    }

    /**
     * Find the _Fields constant that matches name, or null if its not found.
     */
    public static _Fields findByName(String name) {
      return byName.get(name);
    }

    private final short _thriftId;
    private final String _fieldName;

    _Fields(short thriftId, String fieldName) {
      _thriftId = thriftId;
      _fieldName = fieldName;
    }

    public short getThriftFieldId() {
      return _thriftId;
    }

    public String getFieldName() {
      return _fieldName;
    }
  }

  // isset id assignments
  private static final int __ID_ISSET_ID = 0;
  private static final int __PRODUCTID_ISSET_ID = 1;
  private static final int __QUANTITY_ISSET_ID = 2;
  private static final int __STATUS_ISSET_ID = 3;
  private static final int __USERCREATED_ISSET_ID = 4;
  private static final int __USERMODIFIED_ISSET_ID = 5;
  private byte __isset_bitfield = 0;
  public static final Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> metaDataMap;
  static {
    Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> tmpMap = new EnumMap<_Fields, org.apache.thrift.meta_data.FieldMetaData>(_Fields.class);
    tmpMap.put(_Fields.ID, new org.apache.thrift.meta_data.FieldMetaData("id", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.I32)));
    tmpMap.put(_Fields.PRODUCT_ID, new org.apache.thrift.meta_data.FieldMetaData("productId", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.I32)));
    tmpMap.put(_Fields.COLORS, new org.apache.thrift.meta_data.FieldMetaData("colors", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING)));
    tmpMap.put(_Fields.SIZES, new org.apache.thrift.meta_data.FieldMetaData("sizes", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING)));
    tmpMap.put(_Fields.QUANTITY, new org.apache.thrift.meta_data.FieldMetaData("quantity", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.I32)));
    tmpMap.put(_Fields.STATUS, new org.apache.thrift.meta_data.FieldMetaData("status", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.I32)));
    tmpMap.put(_Fields.CREATED_AT, new org.apache.thrift.meta_data.FieldMetaData("createdAt", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING)));
    tmpMap.put(_Fields.MODIFIED_AT, new org.apache.thrift.meta_data.FieldMetaData("modifiedAt", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING)));
    tmpMap.put(_Fields.USER_CREATED, new org.apache.thrift.meta_data.FieldMetaData("userCreated", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.I32)));
    tmpMap.put(_Fields.USER_MODIFIED, new org.apache.thrift.meta_data.FieldMetaData("userModified", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.I32)));
    metaDataMap = Collections.unmodifiableMap(tmpMap);
    org.apache.thrift.meta_data.FieldMetaData.addStructMetaDataMap(TCart.class, metaDataMap);
  }

  public TCart() {
  }

  public TCart(
    int id,
    int productId,
    String colors,
    String sizes,
    int quantity,
    int status,
    String createdAt,
    String modifiedAt,
    int userCreated,
    int userModified)
  {
    this();
    this.id = id;
    setIdIsSet(true);
    this.productId = productId;
    setProductIdIsSet(true);
    this.colors = colors;
    this.sizes = sizes;
    this.quantity = quantity;
    setQuantityIsSet(true);
    this.status = status;
    setStatusIsSet(true);
    this.createdAt = createdAt;
    this.modifiedAt = modifiedAt;
    this.userCreated = userCreated;
    setUserCreatedIsSet(true);
    this.userModified = userModified;
    setUserModifiedIsSet(true);
  }

  /**
   * Performs a deep copy on <i>other</i>.
   */
  public TCart(TCart other) {
    __isset_bitfield = other.__isset_bitfield;
    this.id = other.id;
    this.productId = other.productId;
    if (other.isSetColors()) {
      this.colors = other.colors;
    }
    if (other.isSetSizes()) {
      this.sizes = other.sizes;
    }
    this.quantity = other.quantity;
    this.status = other.status;
    if (other.isSetCreatedAt()) {
      this.createdAt = other.createdAt;
    }
    if (other.isSetModifiedAt()) {
      this.modifiedAt = other.modifiedAt;
    }
    this.userCreated = other.userCreated;
    this.userModified = other.userModified;
  }

  public TCart deepCopy() {
    return new TCart(this);
  }

  @Override
  public void clear() {
    setIdIsSet(false);
    this.id = 0;
    setProductIdIsSet(false);
    this.productId = 0;
    this.colors = null;
    this.sizes = null;
    setQuantityIsSet(false);
    this.quantity = 0;
    setStatusIsSet(false);
    this.status = 0;
    this.createdAt = null;
    this.modifiedAt = null;
    setUserCreatedIsSet(false);
    this.userCreated = 0;
    setUserModifiedIsSet(false);
    this.userModified = 0;
  }

  public int getId() {
    return this.id;
  }

  public TCart setId(int id) {
    this.id = id;
    setIdIsSet(true);
    return this;
  }

  public void unsetId() {
    __isset_bitfield = EncodingUtils.clearBit(__isset_bitfield, __ID_ISSET_ID);
  }

  /** Returns true if field id is set (has been assigned a value) and false otherwise */
  public boolean isSetId() {
    return EncodingUtils.testBit(__isset_bitfield, __ID_ISSET_ID);
  }

  public void setIdIsSet(boolean value) {
    __isset_bitfield = EncodingUtils.setBit(__isset_bitfield, __ID_ISSET_ID, value);
  }

  public int getProductId() {
    return this.productId;
  }

  public TCart setProductId(int productId) {
    this.productId = productId;
    setProductIdIsSet(true);
    return this;
  }

  public void unsetProductId() {
    __isset_bitfield = EncodingUtils.clearBit(__isset_bitfield, __PRODUCTID_ISSET_ID);
  }

  /** Returns true if field productId is set (has been assigned a value) and false otherwise */
  public boolean isSetProductId() {
    return EncodingUtils.testBit(__isset_bitfield, __PRODUCTID_ISSET_ID);
  }

  public void setProductIdIsSet(boolean value) {
    __isset_bitfield = EncodingUtils.setBit(__isset_bitfield, __PRODUCTID_ISSET_ID, value);
  }

  public String getColors() {
    return this.colors;
  }

  public TCart setColors(String colors) {
    this.colors = colors;
    return this;
  }

  public void unsetColors() {
    this.colors = null;
  }

  /** Returns true if field colors is set (has been assigned a value) and false otherwise */
  public boolean isSetColors() {
    return this.colors != null;
  }

  public void setColorsIsSet(boolean value) {
    if (!value) {
      this.colors = null;
    }
  }

  public String getSizes() {
    return this.sizes;
  }

  public TCart setSizes(String sizes) {
    this.sizes = sizes;
    return this;
  }

  public void unsetSizes() {
    this.sizes = null;
  }

  /** Returns true if field sizes is set (has been assigned a value) and false otherwise */
  public boolean isSetSizes() {
    return this.sizes != null;
  }

  public void setSizesIsSet(boolean value) {
    if (!value) {
      this.sizes = null;
    }
  }

  public int getQuantity() {
    return this.quantity;
  }

  public TCart setQuantity(int quantity) {
    this.quantity = quantity;
    setQuantityIsSet(true);
    return this;
  }

  public void unsetQuantity() {
    __isset_bitfield = EncodingUtils.clearBit(__isset_bitfield, __QUANTITY_ISSET_ID);
  }

  /** Returns true if field quantity is set (has been assigned a value) and false otherwise */
  public boolean isSetQuantity() {
    return EncodingUtils.testBit(__isset_bitfield, __QUANTITY_ISSET_ID);
  }

  public void setQuantityIsSet(boolean value) {
    __isset_bitfield = EncodingUtils.setBit(__isset_bitfield, __QUANTITY_ISSET_ID, value);
  }

  public int getStatus() {
    return this.status;
  }

  public TCart setStatus(int status) {
    this.status = status;
    setStatusIsSet(true);
    return this;
  }

  public void unsetStatus() {
    __isset_bitfield = EncodingUtils.clearBit(__isset_bitfield, __STATUS_ISSET_ID);
  }

  /** Returns true if field status is set (has been assigned a value) and false otherwise */
  public boolean isSetStatus() {
    return EncodingUtils.testBit(__isset_bitfield, __STATUS_ISSET_ID);
  }

  public void setStatusIsSet(boolean value) {
    __isset_bitfield = EncodingUtils.setBit(__isset_bitfield, __STATUS_ISSET_ID, value);
  }

  public String getCreatedAt() {
    return this.createdAt;
  }

  public TCart setCreatedAt(String createdAt) {
    this.createdAt = createdAt;
    return this;
  }

  public void unsetCreatedAt() {
    this.createdAt = null;
  }

  /** Returns true if field createdAt is set (has been assigned a value) and false otherwise */
  public boolean isSetCreatedAt() {
    return this.createdAt != null;
  }

  public void setCreatedAtIsSet(boolean value) {
    if (!value) {
      this.createdAt = null;
    }
  }

  public String getModifiedAt() {
    return this.modifiedAt;
  }

  public TCart setModifiedAt(String modifiedAt) {
    this.modifiedAt = modifiedAt;
    return this;
  }

  public void unsetModifiedAt() {
    this.modifiedAt = null;
  }

  /** Returns true if field modifiedAt is set (has been assigned a value) and false otherwise */
  public boolean isSetModifiedAt() {
    return this.modifiedAt != null;
  }

  public void setModifiedAtIsSet(boolean value) {
    if (!value) {
      this.modifiedAt = null;
    }
  }

  public int getUserCreated() {
    return this.userCreated;
  }

  public TCart setUserCreated(int userCreated) {
    this.userCreated = userCreated;
    setUserCreatedIsSet(true);
    return this;
  }

  public void unsetUserCreated() {
    __isset_bitfield = EncodingUtils.clearBit(__isset_bitfield, __USERCREATED_ISSET_ID);
  }

  /** Returns true if field userCreated is set (has been assigned a value) and false otherwise */
  public boolean isSetUserCreated() {
    return EncodingUtils.testBit(__isset_bitfield, __USERCREATED_ISSET_ID);
  }

  public void setUserCreatedIsSet(boolean value) {
    __isset_bitfield = EncodingUtils.setBit(__isset_bitfield, __USERCREATED_ISSET_ID, value);
  }

  public int getUserModified() {
    return this.userModified;
  }

  public TCart setUserModified(int userModified) {
    this.userModified = userModified;
    setUserModifiedIsSet(true);
    return this;
  }

  public void unsetUserModified() {
    __isset_bitfield = EncodingUtils.clearBit(__isset_bitfield, __USERMODIFIED_ISSET_ID);
  }

  /** Returns true if field userModified is set (has been assigned a value) and false otherwise */
  public boolean isSetUserModified() {
    return EncodingUtils.testBit(__isset_bitfield, __USERMODIFIED_ISSET_ID);
  }

  public void setUserModifiedIsSet(boolean value) {
    __isset_bitfield = EncodingUtils.setBit(__isset_bitfield, __USERMODIFIED_ISSET_ID, value);
  }

  public void setFieldValue(_Fields field, Object value) {
    switch (field) {
    case ID:
      if (value == null) {
        unsetId();
      } else {
        setId((Integer)value);
      }
      break;

    case PRODUCT_ID:
      if (value == null) {
        unsetProductId();
      } else {
        setProductId((Integer)value);
      }
      break;

    case COLORS:
      if (value == null) {
        unsetColors();
      } else {
        setColors((String)value);
      }
      break;

    case SIZES:
      if (value == null) {
        unsetSizes();
      } else {
        setSizes((String)value);
      }
      break;

    case QUANTITY:
      if (value == null) {
        unsetQuantity();
      } else {
        setQuantity((Integer)value);
      }
      break;

    case STATUS:
      if (value == null) {
        unsetStatus();
      } else {
        setStatus((Integer)value);
      }
      break;

    case CREATED_AT:
      if (value == null) {
        unsetCreatedAt();
      } else {
        setCreatedAt((String)value);
      }
      break;

    case MODIFIED_AT:
      if (value == null) {
        unsetModifiedAt();
      } else {
        setModifiedAt((String)value);
      }
      break;

    case USER_CREATED:
      if (value == null) {
        unsetUserCreated();
      } else {
        setUserCreated((Integer)value);
      }
      break;

    case USER_MODIFIED:
      if (value == null) {
        unsetUserModified();
      } else {
        setUserModified((Integer)value);
      }
      break;

    }
  }

  public Object getFieldValue(_Fields field) {
    switch (field) {
    case ID:
      return Integer.valueOf(getId());

    case PRODUCT_ID:
      return Integer.valueOf(getProductId());

    case COLORS:
      return getColors();

    case SIZES:
      return getSizes();

    case QUANTITY:
      return Integer.valueOf(getQuantity());

    case STATUS:
      return Integer.valueOf(getStatus());

    case CREATED_AT:
      return getCreatedAt();

    case MODIFIED_AT:
      return getModifiedAt();

    case USER_CREATED:
      return Integer.valueOf(getUserCreated());

    case USER_MODIFIED:
      return Integer.valueOf(getUserModified());

    }
    throw new IllegalStateException();
  }

  /** Returns true if field corresponding to fieldID is set (has been assigned a value) and false otherwise */
  public boolean isSet(_Fields field) {
    if (field == null) {
      throw new IllegalArgumentException();
    }

    switch (field) {
    case ID:
      return isSetId();
    case PRODUCT_ID:
      return isSetProductId();
    case COLORS:
      return isSetColors();
    case SIZES:
      return isSetSizes();
    case QUANTITY:
      return isSetQuantity();
    case STATUS:
      return isSetStatus();
    case CREATED_AT:
      return isSetCreatedAt();
    case MODIFIED_AT:
      return isSetModifiedAt();
    case USER_CREATED:
      return isSetUserCreated();
    case USER_MODIFIED:
      return isSetUserModified();
    }
    throw new IllegalStateException();
  }

  @Override
  public boolean equals(Object that) {
    if (that == null)
      return false;
    if (that instanceof TCart)
      return this.equals((TCart)that);
    return false;
  }

  public boolean equals(TCart that) {
    if (that == null)
      return false;

    boolean this_present_id = true;
    boolean that_present_id = true;
    if (this_present_id || that_present_id) {
      if (!(this_present_id && that_present_id))
        return false;
      if (this.id != that.id)
        return false;
    }

    boolean this_present_productId = true;
    boolean that_present_productId = true;
    if (this_present_productId || that_present_productId) {
      if (!(this_present_productId && that_present_productId))
        return false;
      if (this.productId != that.productId)
        return false;
    }

    boolean this_present_colors = true && this.isSetColors();
    boolean that_present_colors = true && that.isSetColors();
    if (this_present_colors || that_present_colors) {
      if (!(this_present_colors && that_present_colors))
        return false;
      if (!this.colors.equals(that.colors))
        return false;
    }

    boolean this_present_sizes = true && this.isSetSizes();
    boolean that_present_sizes = true && that.isSetSizes();
    if (this_present_sizes || that_present_sizes) {
      if (!(this_present_sizes && that_present_sizes))
        return false;
      if (!this.sizes.equals(that.sizes))
        return false;
    }

    boolean this_present_quantity = true;
    boolean that_present_quantity = true;
    if (this_present_quantity || that_present_quantity) {
      if (!(this_present_quantity && that_present_quantity))
        return false;
      if (this.quantity != that.quantity)
        return false;
    }

    boolean this_present_status = true;
    boolean that_present_status = true;
    if (this_present_status || that_present_status) {
      if (!(this_present_status && that_present_status))
        return false;
      if (this.status != that.status)
        return false;
    }

    boolean this_present_createdAt = true && this.isSetCreatedAt();
    boolean that_present_createdAt = true && that.isSetCreatedAt();
    if (this_present_createdAt || that_present_createdAt) {
      if (!(this_present_createdAt && that_present_createdAt))
        return false;
      if (!this.createdAt.equals(that.createdAt))
        return false;
    }

    boolean this_present_modifiedAt = true && this.isSetModifiedAt();
    boolean that_present_modifiedAt = true && that.isSetModifiedAt();
    if (this_present_modifiedAt || that_present_modifiedAt) {
      if (!(this_present_modifiedAt && that_present_modifiedAt))
        return false;
      if (!this.modifiedAt.equals(that.modifiedAt))
        return false;
    }

    boolean this_present_userCreated = true;
    boolean that_present_userCreated = true;
    if (this_present_userCreated || that_present_userCreated) {
      if (!(this_present_userCreated && that_present_userCreated))
        return false;
      if (this.userCreated != that.userCreated)
        return false;
    }

    boolean this_present_userModified = true;
    boolean that_present_userModified = true;
    if (this_present_userModified || that_present_userModified) {
      if (!(this_present_userModified && that_present_userModified))
        return false;
      if (this.userModified != that.userModified)
        return false;
    }

    return true;
  }

  @Override
  public int hashCode() {
    return 0;
  }

  @Override
  public int compareTo(TCart other) {
    if (!getClass().equals(other.getClass())) {
      return getClass().getName().compareTo(other.getClass().getName());
    }

    int lastComparison = 0;

    lastComparison = Boolean.valueOf(isSetId()).compareTo(other.isSetId());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetId()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.id, other.id);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetProductId()).compareTo(other.isSetProductId());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetProductId()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.productId, other.productId);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetColors()).compareTo(other.isSetColors());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetColors()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.colors, other.colors);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetSizes()).compareTo(other.isSetSizes());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetSizes()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.sizes, other.sizes);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetQuantity()).compareTo(other.isSetQuantity());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetQuantity()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.quantity, other.quantity);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetStatus()).compareTo(other.isSetStatus());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetStatus()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.status, other.status);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetCreatedAt()).compareTo(other.isSetCreatedAt());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetCreatedAt()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.createdAt, other.createdAt);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetModifiedAt()).compareTo(other.isSetModifiedAt());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetModifiedAt()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.modifiedAt, other.modifiedAt);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetUserCreated()).compareTo(other.isSetUserCreated());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetUserCreated()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.userCreated, other.userCreated);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetUserModified()).compareTo(other.isSetUserModified());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetUserModified()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.userModified, other.userModified);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    return 0;
  }

  public _Fields fieldForId(int fieldId) {
    return _Fields.findByThriftId(fieldId);
  }

  public void read(org.apache.thrift.protocol.TProtocol iprot) throws org.apache.thrift.TException {
    schemes.get(iprot.getScheme()).getScheme().read(iprot, this);
  }

  public void write(org.apache.thrift.protocol.TProtocol oprot) throws org.apache.thrift.TException {
    schemes.get(oprot.getScheme()).getScheme().write(oprot, this);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder("TCart(");
    boolean first = true;

    sb.append("id:");
    sb.append(this.id);
    first = false;
    if (!first) sb.append(", ");
    sb.append("productId:");
    sb.append(this.productId);
    first = false;
    if (!first) sb.append(", ");
    sb.append("colors:");
    if (this.colors == null) {
      sb.append("null");
    } else {
      sb.append(this.colors);
    }
    first = false;
    if (!first) sb.append(", ");
    sb.append("sizes:");
    if (this.sizes == null) {
      sb.append("null");
    } else {
      sb.append(this.sizes);
    }
    first = false;
    if (!first) sb.append(", ");
    sb.append("quantity:");
    sb.append(this.quantity);
    first = false;
    if (!first) sb.append(", ");
    sb.append("status:");
    sb.append(this.status);
    first = false;
    if (!first) sb.append(", ");
    sb.append("createdAt:");
    if (this.createdAt == null) {
      sb.append("null");
    } else {
      sb.append(this.createdAt);
    }
    first = false;
    if (!first) sb.append(", ");
    sb.append("modifiedAt:");
    if (this.modifiedAt == null) {
      sb.append("null");
    } else {
      sb.append(this.modifiedAt);
    }
    first = false;
    if (!first) sb.append(", ");
    sb.append("userCreated:");
    sb.append(this.userCreated);
    first = false;
    if (!first) sb.append(", ");
    sb.append("userModified:");
    sb.append(this.userModified);
    first = false;
    sb.append(")");
    return sb.toString();
  }

  public void validate() throws org.apache.thrift.TException {
    // check for required fields
    // check for sub-struct validity
  }

  private void writeObject(java.io.ObjectOutputStream out) throws java.io.IOException {
    try {
      write(new org.apache.thrift.protocol.TCompactProtocol(new org.apache.thrift.transport.TIOStreamTransport(out)));
    } catch (org.apache.thrift.TException te) {
      throw new java.io.IOException(te);
    }
  }

  private void readObject(java.io.ObjectInputStream in) throws java.io.IOException, ClassNotFoundException {
    try {
      // it doesn't seem like you should have to do this, but java serialization is wacky, and doesn't call the default constructor.
      __isset_bitfield = 0;
      read(new org.apache.thrift.protocol.TCompactProtocol(new org.apache.thrift.transport.TIOStreamTransport(in)));
    } catch (org.apache.thrift.TException te) {
      throw new java.io.IOException(te);
    }
  }

  private static class TCartStandardSchemeFactory implements SchemeFactory {
    public TCartStandardScheme getScheme() {
      return new TCartStandardScheme();
    }
  }

  private static class TCartStandardScheme extends StandardScheme<TCart> {

    public void read(org.apache.thrift.protocol.TProtocol iprot, TCart struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TField schemeField;
      iprot.readStructBegin();
      while (true)
      {
        schemeField = iprot.readFieldBegin();
        if (schemeField.type == org.apache.thrift.protocol.TType.STOP) { 
          break;
        }
        switch (schemeField.id) {
          case 1: // ID
            if (schemeField.type == org.apache.thrift.protocol.TType.I32) {
              struct.id = iprot.readI32();
              struct.setIdIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 2: // PRODUCT_ID
            if (schemeField.type == org.apache.thrift.protocol.TType.I32) {
              struct.productId = iprot.readI32();
              struct.setProductIdIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 3: // COLORS
            if (schemeField.type == org.apache.thrift.protocol.TType.STRING) {
              struct.colors = iprot.readString();
              struct.setColorsIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 4: // SIZES
            if (schemeField.type == org.apache.thrift.protocol.TType.STRING) {
              struct.sizes = iprot.readString();
              struct.setSizesIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 5: // QUANTITY
            if (schemeField.type == org.apache.thrift.protocol.TType.I32) {
              struct.quantity = iprot.readI32();
              struct.setQuantityIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 6: // STATUS
            if (schemeField.type == org.apache.thrift.protocol.TType.I32) {
              struct.status = iprot.readI32();
              struct.setStatusIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 7: // CREATED_AT
            if (schemeField.type == org.apache.thrift.protocol.TType.STRING) {
              struct.createdAt = iprot.readString();
              struct.setCreatedAtIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 8: // MODIFIED_AT
            if (schemeField.type == org.apache.thrift.protocol.TType.STRING) {
              struct.modifiedAt = iprot.readString();
              struct.setModifiedAtIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 9: // USER_CREATED
            if (schemeField.type == org.apache.thrift.protocol.TType.I32) {
              struct.userCreated = iprot.readI32();
              struct.setUserCreatedIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 10: // USER_MODIFIED
            if (schemeField.type == org.apache.thrift.protocol.TType.I32) {
              struct.userModified = iprot.readI32();
              struct.setUserModifiedIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          default:
            org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
        }
        iprot.readFieldEnd();
      }
      iprot.readStructEnd();

      // check for required fields of primitive type, which can't be checked in the validate method
      struct.validate();
    }

    public void write(org.apache.thrift.protocol.TProtocol oprot, TCart struct) throws org.apache.thrift.TException {
      struct.validate();

      oprot.writeStructBegin(STRUCT_DESC);
      oprot.writeFieldBegin(ID_FIELD_DESC);
      oprot.writeI32(struct.id);
      oprot.writeFieldEnd();
      oprot.writeFieldBegin(PRODUCT_ID_FIELD_DESC);
      oprot.writeI32(struct.productId);
      oprot.writeFieldEnd();
      if (struct.colors != null) {
        oprot.writeFieldBegin(COLORS_FIELD_DESC);
        oprot.writeString(struct.colors);
        oprot.writeFieldEnd();
      }
      if (struct.sizes != null) {
        oprot.writeFieldBegin(SIZES_FIELD_DESC);
        oprot.writeString(struct.sizes);
        oprot.writeFieldEnd();
      }
      oprot.writeFieldBegin(QUANTITY_FIELD_DESC);
      oprot.writeI32(struct.quantity);
      oprot.writeFieldEnd();
      oprot.writeFieldBegin(STATUS_FIELD_DESC);
      oprot.writeI32(struct.status);
      oprot.writeFieldEnd();
      if (struct.createdAt != null) {
        oprot.writeFieldBegin(CREATED_AT_FIELD_DESC);
        oprot.writeString(struct.createdAt);
        oprot.writeFieldEnd();
      }
      if (struct.modifiedAt != null) {
        oprot.writeFieldBegin(MODIFIED_AT_FIELD_DESC);
        oprot.writeString(struct.modifiedAt);
        oprot.writeFieldEnd();
      }
      oprot.writeFieldBegin(USER_CREATED_FIELD_DESC);
      oprot.writeI32(struct.userCreated);
      oprot.writeFieldEnd();
      oprot.writeFieldBegin(USER_MODIFIED_FIELD_DESC);
      oprot.writeI32(struct.userModified);
      oprot.writeFieldEnd();
      oprot.writeFieldStop();
      oprot.writeStructEnd();
    }

  }

  private static class TCartTupleSchemeFactory implements SchemeFactory {
    public TCartTupleScheme getScheme() {
      return new TCartTupleScheme();
    }
  }

  private static class TCartTupleScheme extends TupleScheme<TCart> {

    @Override
    public void write(org.apache.thrift.protocol.TProtocol prot, TCart struct) throws org.apache.thrift.TException {
      TTupleProtocol oprot = (TTupleProtocol) prot;
      BitSet optionals = new BitSet();
      if (struct.isSetId()) {
        optionals.set(0);
      }
      if (struct.isSetProductId()) {
        optionals.set(1);
      }
      if (struct.isSetColors()) {
        optionals.set(2);
      }
      if (struct.isSetSizes()) {
        optionals.set(3);
      }
      if (struct.isSetQuantity()) {
        optionals.set(4);
      }
      if (struct.isSetStatus()) {
        optionals.set(5);
      }
      if (struct.isSetCreatedAt()) {
        optionals.set(6);
      }
      if (struct.isSetModifiedAt()) {
        optionals.set(7);
      }
      if (struct.isSetUserCreated()) {
        optionals.set(8);
      }
      if (struct.isSetUserModified()) {
        optionals.set(9);
      }
      oprot.writeBitSet(optionals, 10);
      if (struct.isSetId()) {
        oprot.writeI32(struct.id);
      }
      if (struct.isSetProductId()) {
        oprot.writeI32(struct.productId);
      }
      if (struct.isSetColors()) {
        oprot.writeString(struct.colors);
      }
      if (struct.isSetSizes()) {
        oprot.writeString(struct.sizes);
      }
      if (struct.isSetQuantity()) {
        oprot.writeI32(struct.quantity);
      }
      if (struct.isSetStatus()) {
        oprot.writeI32(struct.status);
      }
      if (struct.isSetCreatedAt()) {
        oprot.writeString(struct.createdAt);
      }
      if (struct.isSetModifiedAt()) {
        oprot.writeString(struct.modifiedAt);
      }
      if (struct.isSetUserCreated()) {
        oprot.writeI32(struct.userCreated);
      }
      if (struct.isSetUserModified()) {
        oprot.writeI32(struct.userModified);
      }
    }

    @Override
    public void read(org.apache.thrift.protocol.TProtocol prot, TCart struct) throws org.apache.thrift.TException {
      TTupleProtocol iprot = (TTupleProtocol) prot;
      BitSet incoming = iprot.readBitSet(10);
      if (incoming.get(0)) {
        struct.id = iprot.readI32();
        struct.setIdIsSet(true);
      }
      if (incoming.get(1)) {
        struct.productId = iprot.readI32();
        struct.setProductIdIsSet(true);
      }
      if (incoming.get(2)) {
        struct.colors = iprot.readString();
        struct.setColorsIsSet(true);
      }
      if (incoming.get(3)) {
        struct.sizes = iprot.readString();
        struct.setSizesIsSet(true);
      }
      if (incoming.get(4)) {
        struct.quantity = iprot.readI32();
        struct.setQuantityIsSet(true);
      }
      if (incoming.get(5)) {
        struct.status = iprot.readI32();
        struct.setStatusIsSet(true);
      }
      if (incoming.get(6)) {
        struct.createdAt = iprot.readString();
        struct.setCreatedAtIsSet(true);
      }
      if (incoming.get(7)) {
        struct.modifiedAt = iprot.readString();
        struct.setModifiedAtIsSet(true);
      }
      if (incoming.get(8)) {
        struct.userCreated = iprot.readI32();
        struct.setUserCreatedIsSet(true);
      }
      if (incoming.get(9)) {
        struct.userModified = iprot.readI32();
        struct.setUserModifiedIsSet(true);
      }
    }
  }

}

