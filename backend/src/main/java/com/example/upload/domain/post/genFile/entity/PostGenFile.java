package com.example.upload.domain.post.genFile.entity;

import com.example.upload.domain.base.genFile.genFile.entity.GenFile;
import com.example.upload.domain.post.post.entity.Post;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PostGenFile extends GenFile {

	public enum TypeCode {
		attachment, thumbnail
	}

	@ManyToOne(fetch = FetchType.LAZY)
	private Post post;

	@Enumerated(EnumType.STRING)
	private TypeCode typeCode;

	public PostGenFile(Post post, TypeCode typeCode, int fileNo, String originalFileName,
		String metadata, String fileDateDir, String fileExt, String fileExtTypeCode, String fileExtType2Code,
		String fileName, long fileSize) {
		super(fileNo, originalFileName, metadata, fileDateDir, fileExt, fileExtTypeCode, fileExtType2Code, fileName,
			fileSize);
		this.post = post;
		this.typeCode = typeCode;
	}

	@Override
	protected long getOwnerModelId() {
		return post.getId();
	}

	@Override
	protected String getTypeCodeAsStr() {
		return typeCode.name();
	}
}
